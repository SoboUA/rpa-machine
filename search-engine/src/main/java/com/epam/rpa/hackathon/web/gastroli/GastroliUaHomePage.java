package com.epam.rpa.hackathon.web.gastroli;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import com.epam.rpa.hackathon.web.gastroli.model.GastroliEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

    public void scrapEventLinks(){

    }

}


