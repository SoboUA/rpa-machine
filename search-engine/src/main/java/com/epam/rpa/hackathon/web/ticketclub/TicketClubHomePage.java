package com.epam.rpa.hackathon.web.ticketclub;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import com.epam.rpa.hackathon.web.IEvent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @FindBy(xpath = "//*[@class='categories']//*[contains(text(), 'Музика')]")
    private WebElement musicCategory;

    @FindBy(xpath = "//*[@class='categories']//*[contains(text(), 'Театр')]")
    private WebElement theatreCategory;

    @FindBy(xpath = "//*[@class='categories']//*[contains(text(), 'Кіно')]")
    private WebElement movieCategory;

    @FindBy(xpath = "//*[@class='categories']//*[contains(text(), 'Дітям')]")
    private WebElement forChildrenCategory;

    @FindBy(xpath = "//*[@class='categories']//*[contains(text(), 'Інше')]")
    private WebElement otherCategory;

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

    public List<IEvent> getAllEventsFromSite() {
        List<IEvent> events = new ArrayList<>();
        events.addAll(this.getMusicEvents());
        events.addAll(this.getTheatreEvents());
        events.addAll(this.getMovieEvents());
        events.addAll(this.getForChildrenEvents());
        events.addAll(this.getOtherEvents());
        return events;
    }

    public List<TicketClubEvent> getMusicEvents() {

        musicCategory.click();
        return this.getAllEventsFromPages(TicketClubCategory.MUSIC);
    }

    public List<TicketClubEvent> getTheatreEvents() {

        theatreCategory.click();
        return this.getAllEventsFromPages(TicketClubCategory.THEATRE);
    }

    public List<TicketClubEvent> getMovieEvents() {

        movieCategory.click();
        return this.getAllEventsFromPages(TicketClubCategory.MOVIE);
    }

    public List<TicketClubEvent> getForChildrenEvents() {

        forChildrenCategory.click();
        return this.getAllEventsFromPages(TicketClubCategory.FOR_CHILDREN);
    }

    public List<TicketClubEvent> getOtherEvents() {

        otherCategory.click();
        return this.getAllEventsFromPages(TicketClubCategory.OTHER);
    }

    public List<TicketClubEvent> getAllEventsFromPages(TicketClubCategory category) {
        List<TicketClubEvent> events = new ArrayList<>();

        do {
            events.addAll(allEvents.stream()
                    .map(element -> {
                        return new TicketClubEvent(category.toString(),
                                findSubElementBy(element, By.xpath(".//*[@class='title']")).getText().trim(),
                                findSubElementBy(element, By.xpath("//*[@class='event-wrap']//*[@class='date']")).getText()
                                        .replaceAll("[а-яА-Я]|\\s|[a-zA-Z]", "") +
                                        " " +
                                        findSubElementBy(element, By.xpath(".//*[@class='time']")).getText().trim(),
                                findSubElementBy(element, By.xpath(".//a[contains(@href, 'location')]")).getText().trim(),
                                findSubElementBy(element, By.xpath(".//*[@class='descr']/p")).getText().trim(),
                                findSubElementBy(element, By.xpath(".//img[@class='img-event']")).getAttribute("src")
                        );
                    }).collect(Collectors.toList()));
        } while (goToNextPage());
        return events;
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

    public TicketClubHomePage setDateTo(LocalDate date) {
        dateTo.click();
        date = this.defaultIfFromPast(date, LocalDate.now());

        if (StringUtils.equalsIgnoreCase(months.get(date.getMonthValue()), dateToMonth.getText()) &&
                Integer.parseInt(dateToYear.getText()) == date.getYear()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

    private boolean goToNextPage() {
        boolean active = true;
        try {
            driver.findElement(By.xpath("//*[@class='pagination-custom']//a[@rel='next']")).click();
        } catch (Exception e) {
            active = false;
        }
        return active;

    }
}

