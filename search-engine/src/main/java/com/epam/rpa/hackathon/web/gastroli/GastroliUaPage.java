package com.epam.rpa.hackathon.web.gastroli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class GastroliUaPage {

    protected WebDriver driver;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final long DEFAULT_TIMEOUT = 20;
    private LocalDate from;
    private LocalDate to;

    public GastroliUaPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts()
                .implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public void setDataRange(LocalDate from, LocalDate to){
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public WebElement findSubElementBy(WebElement element, By expression) {
        return element.findElement(expression);
    }
}
