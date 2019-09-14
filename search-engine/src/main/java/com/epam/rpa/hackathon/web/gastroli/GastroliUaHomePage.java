package com.epam.rpa.hackathon.web.gastroli;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import com.epam.rpa.hackathon.web.gastroli.data.DataFilter;
import com.epam.rpa.hackathon.web.gastroli.model.GastroliEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<GastroliEvent> getEvents() {
        exploreAllEvents();

        List<String> links = scrapEventLinks();

        System.out.println(links);

        return links.stream()
                .limit(20)
                .map(this::processEvent)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public GastroliEvent processEvent(String link) {

        GastroliEvent event = null;

        try {
            logger.warn("Started to process : " + link);
            driver.get(link);
            event = new GastroliEventPage(driver).returnEvent(getFrom(), getTo());
            logger.warn("Event : " + event.toString());
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return event;
    }

    public GastroliUaHomePage setup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.visibilityOf(language)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[text() = 'UA']"))).click();

            wait.until(ExpectedConditions.visibilityOf(cityDropDown)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[text() = 'Львів']"))).click();
        } catch (Exception e) {
            logger.warn("Logger : " + e.getMessage());
        }

        return this;
    }

    public List<String> scrapEventLinks() {
        List<WebElement> allEvents = driver.findElements(By.xpath("//a[@class='list-item-image']"));

        return allEvents.stream()
                .map(element -> element.getAttribute("href"))
                .collect(Collectors.toList());
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

}


