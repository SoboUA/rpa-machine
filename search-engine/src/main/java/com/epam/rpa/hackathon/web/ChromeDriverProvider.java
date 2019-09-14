package com.epam.rpa.hackathon.web;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

public class ChromeDriverProvider implements IWebDriverProvider {

    @Value("${webdriver.chrome.driver}")
    private String driverPath;

    public WebDriver getDriver() {
//        System.setProperty(Property.WEBDRIVER_CHROME_DRIVER.toString(), driverPath);
        System.setProperty(Property.WEBDRIVER_CHROME_DRIVER.toString(),
                PropertyUtil.getProperty(Property.WEBDRIVER_CHROME_DRIVER));

        ChromeOptions chromeOptions = new ChromeOptions()
                .setPageLoadStrategy(PageLoadStrategy.NORMAL)
                .addArguments("--start-maximized");

        return new ChromeDriver(chromeOptions);
    }
}
