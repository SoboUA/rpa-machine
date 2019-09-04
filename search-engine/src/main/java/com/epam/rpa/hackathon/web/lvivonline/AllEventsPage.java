package com.epam.rpa.hackathon.web.lvivonline;

import com.epam.rpa.hackathon.web.lvivonline.model.LvivOnlineEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllEventsPage extends LvivOnlinePage {

    @FindBy(xpath = "//*[@class='events--list__category']/article")
    private List<WebElement> eventsList;

    @FindBy(xpath = "//*[@class='page_navigation']/*")
    private List<WebElement> listOfPages;

    public AllEventsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<LvivOnlineEvent> getEventsFromAllPages() {
        List<LvivOnlineEvent> eventsList = new ArrayList<>();
        for (int i = 1; i < listOfPages.size() + 1; i++) {
            this.goToPage(i);
            eventsList.addAll(this.getAllEventsFromPage());
        }

        return eventsList;
    }

    public List<LvivOnlineEvent> getAllEventsFromPage() {
        return this.eventsList.stream()
                .map(element ->
                        new LvivOnlineEvent(findSubElementBy(element, By.xpath(".//*[@class='category-name']/a")).getText(),
                                findSubElementBy(element, By.xpath(".//*[@class='title']/a")).getText(),
                                findSubElementBy(element, By.xpath(".//*[@class='sortdate']//span[@itemprop='startDate']")).getAttribute("content"),
                                findSubElementBy(element, By.xpath(".//*[@class='sortdate']//p[@class='place']/span")).getText(),
                                findSubElementBy(element, By.xpath(".//*[@class='description text']")).getText())
                ).collect(Collectors.toList());

    }

    public AllEventsPage goToPage(int pageNumber) {
        this.listOfPages.get(pageNumber - 1).click();

        return this;
    }


}
