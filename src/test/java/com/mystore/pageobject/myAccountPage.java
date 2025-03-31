package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class myAccountPage {

    //Initializing object of WebDriver
    WebDriver ldriver;

    //Constructor
    public myAccountPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//div[@id='center_column']//p[1]")
    WebElement registeredToast;

    @FindBy(xpath = "//a[@title='View my customer account']//span")
    WebElement registeredUserName;

    @FindBy(xpath = "//a[@title='Log me out']")
    WebElement signOut;

    @FindBy(name ="search_query")
    WebElement searchBox;

    @FindBy(name ="submit_search")
    WebElement submit_search;

    @FindBy(linkText = "Women")
    WebElement WomenMenu;

    @FindBy(linkText="T-shirts")
    WebElement TShirtMenu;

    public String registeredToastMessage() {
        return registeredToast.getText();
    }

    public String registeredUserName() {
        return registeredUserName.getText();
    }

    public void signOut() {
        signOut.click();

    }

    public void EnterDataInSearchBox(String searchKey)
    {
        searchBox.sendKeys(searchKey);
    }

    public void ClickOnSearchButton()
    {
        submit_search.click();

    }


    public void MouseOverTShirtMenu()
    {
        Actions actions=new Actions(ldriver);
        actions.moveToElement(WomenMenu).moveToElement(TShirtMenu).click().perform();
    }
}
