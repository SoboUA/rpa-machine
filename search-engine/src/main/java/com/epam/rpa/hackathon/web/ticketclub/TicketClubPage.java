package com.epam.rpa.hackathon.web.ticketclub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public abstract class TicketClubPage {

    protected WebDriver driver;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final long DEFAULT_TIMEOUT = 20;

    public TicketClubPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts()
                .implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public WebElement findSubElementBy(WebElement element, By expression) {
        return element.findElement(expression);
    }
}
