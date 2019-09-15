package com.epam.rpa.hackathon.web.gastroli;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.gastroli.model.GastroliEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class GastroliUaHomePage extends GastroliUaPage {

    private Logger logger = LoggerFactory.getLogger(GastroliUaHomePage.class);

    public static final String homePageUrl = PropertyUtil.getProperty(Property.GASTROLI_UA_HOMEPAGE);

    @FindBy(xpath = "//div[@class='search-line']/div[@class='search-select']")
    private WebElement cityDropDown;

    @FindBy(xpath = "//div[@class='header-right']/div[2]")
    private WebElement language;

    @FindBy(xpath = "//div[contains(@class,'col-xs-9')]")
    private List<WebElement> categories;

    public GastroliUaHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.get(homePageUrl);
    }

    public GastroliUaHomePage setup() {
        try {
            Actions actions = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.visibilityOf(language)).click();
            //actions.moveToElement(language).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[text() = 'UA']"))).click();

            wait.until(ExpectedConditions.visibilityOf(cityDropDown)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[text() = 'Львів']"))).click();

        } catch (Exception e) {
            logger.warn("Logger : " + e.getMessage());
        }

        return this;
    }

    public List<IEvent> getCategorizedEvents() {
        List<IEvent> events = new ArrayList<>();
        Actions actions = new Actions(driver);

        List<WebElement> filteredCategories = filterCategories(categories);

        WebDriverWait wait = new WebDriverWait(driver, 15);

        for (WebElement category : filteredCategories) {
            String categoryStr = category.getText();
            logger.warn("Started to process " + categoryStr);

            actions.moveToElement(category).perform();
            category.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }

            logger.warn("Started to explore events");

            exploreAllEvents();

            logger.warn("Started to scrap data");
            List<GastroliEvent> gastroliEvents = scrapData(categoryStr);

            logger.warn("Started to unite data");
            gastroliEvents = unite(gastroliEvents);

            events.addAll(gastroliEvents);

            logger.warn("Appended category set");
        }

        return events;
    }

    private List<WebElement> filterCategories(List<WebElement> list) {
        String allCategories = "Усі категорії";
        String suggestion = "Найкращі події";

        return list.stream().filter(e -> !e.getText().contains(allCategories)
                && !e.getText().contains(suggestion)).collect(Collectors.toList());
    }

    private void exploreAllEvents() {

        int count = 0;
        int limit = 200;

        while (true) {
            if (++count > limit) {
                logger.warn("Limit");
                break;
            }

            logger.warn("New More button press cycle");

            try {
                WebElement more = driver.findElement(By.xpath("//div[contains(text(), 'Показано')]"));
                more.click();
            } catch (Exception e) {
                logger.warn("Can`t find More button");
                break;
            }
        }
    }

    private List<GastroliEvent> scrapData(String category) {

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='list-item-inner']"));

        return items.stream().map(element ->
                new GastroliEvent(category,
                        findSubElementBy(element, By.xpath(".//div[@class='list-item-desc']/a")).getText(),
                        findSubElementBy(element, By.xpath(".//p[2]")).getText(),
                        findSubElementBy(element, By.xpath(".//p[1]")).getText(),
                        "",
                        findSubElementBy(element, By.xpath(".//img")).getAttribute("src")))
                .collect(Collectors.toList());
    }

    public WebElement findSubElementBy(WebElement element, By expression) {
        return element.findElement(expression);
    }

    private List<GastroliEvent> unite(List<GastroliEvent> list) {

        Collection<List<GastroliEvent>> grouped = list.stream()
                .collect(groupingBy(GastroliEvent::getTitle)).values();

        return grouped.stream().map(GastroliEvent.UniqueEvent::new).map(GastroliEvent.UniqueEvent::simplify).flatMap(List::stream)
                .collect(Collectors.toList());
    }


}


