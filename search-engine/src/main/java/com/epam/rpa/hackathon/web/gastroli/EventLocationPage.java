package com.epam.rpa.hackathon.web.gastroli;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventLocationPage extends GastroliUaPage {

    @FindBy(xpath = "//div[@class='place-info']/div[1]/p/a")
    private WebElement address;

    public EventLocationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getAddress(){
        return address.getText();
    }

}
