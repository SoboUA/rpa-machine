package com.epam.rpa.hackathon.web.gastroli;

import com.epam.rpa.hackathon.web.gastroli.model.GastroliEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GastroliEventPage extends GastroliUaPage {

    @FindBy(xpath = "//span[@class='event-category-item']")
    private WebElement category;

    @FindBy(xpath = "//h1[@class='event-title']")
    private WebElement title;

    @FindBy(xpath = "//div[contains(@class,'event-info-place')]/p")
    private WebElement place;

    @FindBy(xpath = "//div[contains(@class,'event-info-date')]/p")
    private WebElement date;

    @FindBy(xpath = "//div[contains(@class,'event-text')]")
    private WebElement description;

    @FindBy(xpath = "//div[contains(@class,'event-info')]//a")
    private WebElement locationElement;

    @FindBy(xpath = "//span[@class='event-category-item']/img")
    private WebElement image;

    public GastroliEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GastroliEvent returnEvent() {

        String locationLink = locationElement.getAttribute("href");

        String categoryStr = category.getText();
        String titleStr = title.getText();
        String dateStr = date.getText();

        String descriptionStr = description.getText();
        String imageLink = image.getAttribute("src");
        String placeStr = place.getText() + processAddress(locationLink);

        return new GastroliEvent(categoryStr, titleStr, dateStr, placeStr, descriptionStr, imageLink);
    }

    public String processAddress(String link) {
        driver.get(link);
        return new EventLocationPage(driver).getAddress();

    }


}
