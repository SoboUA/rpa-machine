package com.epam.rpa.hackathon.web.lvivonline;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.util.PropertyUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LvivOnlineHomePage extends LvivOnlinePage {

    public static final String homePageUrl = PropertyUtil.getProperty(Property.LVIV_ONLINE_HOMEPAGE);

    @FindBy(id = "active")
    private WebElement eventsSubMenu;

    @FindBy(xpath = "//a[@class='menu--level--up' and text()='УСІ ПОДІЇ']")
    private WebElement allEvents;

    public LvivOnlineHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.get(homePageUrl);
    }


    public AllEventsPage goToAllEventsPage() {
        try {
            WebDriverWait waiter = new WebDriverWait(driver, 10);
            eventsSubMenu.click();
//            Thread.sleep(1000);

            waiter.until(ExpectedConditions.elementToBeClickable(allEvents)).click();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AllEventsPage(driver);
    }

}
