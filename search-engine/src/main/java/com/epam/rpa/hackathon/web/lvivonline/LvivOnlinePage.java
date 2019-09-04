package com.epam.rpa.hackathon.web.lvivonline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class LvivOnlinePage {

    protected WebDriver driver;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final long DEFAULT_TIMEOUT = 20;

    public LvivOnlinePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts()
                .implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public WebElement findSubElementBy(WebElement element, By expression) {
        return element.findElement(expression);
    }

}
