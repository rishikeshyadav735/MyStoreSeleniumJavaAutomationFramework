package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accountCreationDetails {

    //Initializing object of WebDriver
    WebDriver ldriver;

    //Constructor
    public accountCreationDetails(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver,this);
    }

    //Identifying WebElements
    @FindBy(id = "id_gender1")
    WebElement titleMr;

    @FindBy(id = "customer_firstname")
    WebElement firstName;

    @FindBy(id = "customer_lastname")
    WebElement lastName;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(id = "submitAccount")
    WebElement registerButton;



    //Action on WebElements
    public void selectTitle(){
        titleMr.click();
    }

    public void customerFirstName(String customer_FirstName){firstName.sendKeys(customer_FirstName);}

    public void customerLastName(String customer_LastName){lastName.sendKeys(customer_LastName);}

    public void customerPassword(String pass){password.sendKeys(pass);}

    public void registerButton(){registerButton.click();}


}
