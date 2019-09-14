package com.epam.rpa.hackathon.web.ticketclub;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.lvivonline.model.LvivOnlineEvent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketClubHomePage extends TicketClubPage {

    private static Map<Integer, String> months = new HashMap<Integer, String>() {{
        put(1, "Січень");
        put(2, "Лютий");
        put(3, "Березень");
        put(4, "Квітень");
        put(5, "Травень");
        put(6, "Червень");
        put(7, "Липень");
        put(8, "Серпень");
        put(9, "Вересень");
        put(10, "Жовтень");
        put(11, "Листопад");
        put(12, "Грудень");
    }};

    //    @Value("${ticket.club.homepage}")
//    private String homePageUrl;
    private String homePageUrl = PropertyUtil.getProperty(Property.TICKET_CLUB_HOMEPAGE);

    @FindBy(xpath = "//*[@class='filter-item city']//*[@class='btn-select btn-select-img']")
    private WebElement cityDropdown;

    @FindBy(xpath = "//*[@class='filter-item city']//li[contains(text(), 'Львів')]")
    private WebElement lvivCitySelector;

    @FindBy(xpath = "//*[@class='filter-item loc']//*[@class='btn-select btn-select-img']")
    private WebElement locationDropdown;

    @FindBy(xpath = "//*[@class='filter-item loc']//li[@data-id='all']")
    private WebElement allLocationsSelector;

    @FindBy(xpath = "//*[@class='filter-item date']//span[@class='btn-select btn-pick-date-1']")
    private WebElement dateFrom;

    @FindBy(xpath = "//*[@id='filter-datepicker']//span[@class='ui-datepicker-month']")
    private WebElement dateFromMonth;

    @FindBy(xpath = "//*[@id='filter-datepicker']//span[@class='ui-datepicker-year']")
    private WebElement dateFromYear;

    @FindBy(xpath = "//*[@class='filter-item date']//span[@class='btn-select btn-pick-date-2']")
    private WebElement dateTo;

    @FindBy(xpath = "//*[@id='filter-datepicker-2']//span[@class='ui-datepicker-month']")
    private WebElement dateToMonth;

    @FindBy(xpath = "//*[@id='filter-datepicker-2']//span[@class='ui-datepicker-year']")
    private WebElement dateToYear;

    @FindBy(xpath = "//*[@id='events']//div[@class='event-wrap']")
    private List<WebElement> allEvents;

    public TicketClubHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.get(homePageUrl);
    }

    public TicketClubHomePage selectLvivCity() {
        cityDropdown.click();
        lvivCitySelector.click();
        return this;
    }

    public TicketClubHomePage selectAllLocations() {
        locationDropdown.click();
        allLocationsSelector.click();
        return this;
    }

    public TicketClubHomePage setDateFrom(LocalDate date) {
        dateFrom.click();
        date = this.defaultIfFromPast(date, LocalDate.now());

        if (StringUtils.equalsIgnoreCase(months.get(date.getMonthValue()), dateFromMonth.getText()) &&
                Integer.parseInt(dateFromYear.getText()) == date.getYear()) {
            driver.findElement(
                    By.xpath("//*[@id='filter-datepicker']//*[@class='ui-datepicker-calendar']//a[text()='" + date.getDayOfMonth() + "']"))
                    .click();
        }

        //TODO Implement case when current month is not one we need

        return this;
    }

    public List<TicketClubEvent> getAllEventsFromPage() {

        return allEvents.stream()
                .map(element ->
                        new TicketClubEvent(findSubElementBy(element, By.xpath(".//*[@class='category-name']/a")).getText(),
                                findSubElementBy(element, By.xpath(".//*[@class='title']/a")).getText(),
                                findSubElementBy(element, By.xpath(".//*[@class='sortdate']//span[@itemprop='startDate']")).getAttribute("content"),
                                findSubElementBy(element, By.xpath(".//*[@class='sortdate']//p[@class='place']/span")).getText(),
                                findSubElementBy(element, By.xpath(".//*[@class='description text']")).getText())
                ).collect(Collectors.toList());
    }

    public TicketClubHomePage setDateTo(LocalDate date) {
        dateTo.click();
        date = this.defaultIfFromPast(date, LocalDate.now());

        if (StringUtils.equalsIgnoreCase(months.get(date.getMonthValue()), dateFromMonth.getText()) &&
                Integer.parseInt(dateFromYear.getText()) == date.getYear()) {
            driver.findElement(
                    By.xpath("//*[@id='filter-datepicker-2']//*[@class='ui-datepicker-calendar']//a[text()='" + date.getDayOfMonth() + "']"))
                    .click();
        }

        //TODO Implement case when current month is not one we need

        return this;
    }

    private LocalDate defaultIfFromPast(LocalDate date, LocalDate defaultDate) {
        return date.isAfter(LocalDate.now()) ? date : defaultDate;
    }

}
