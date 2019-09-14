package com.epam.rpa.hackathon.web.gastroli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class GastroliUaPage {

    protected WebDriver driver;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final long DEFAULT_TIMEOUT = 20;
    private String from;
    private String to;

    public GastroliUaPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts()
                .implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public void setDataRange(String from, String to){
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public WebElement findSubElementBy(WebElement element, By expression) {
        return element.findElement(expression);
    }
}
