package com.epam.rpa.hackathon.web.lvivonline;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import com.epam.rpa.hackathon.web.IWebDriverProvider;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LvivOnlineWebDriverProvider implements IWebDriverProvider {

    @Override
    public WebDriver getDriver() {
        System.setProperty(Property.WEBDRIVER_CHROME_DRIVER.toString(),
                PropertyUtil.getProperty(Property.WEBDRIVER_CHROME_DRIVER));

        ChromeOptions chromeOptions = new ChromeOptions()
                .setPageLoadStrategy(PageLoadStrategy.NORMAL)
                .addArguments("--start-maximized");

        return new ChromeDriver(chromeOptions);
    }
}
