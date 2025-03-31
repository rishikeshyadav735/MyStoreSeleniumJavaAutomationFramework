package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class signUpOrLoginPage {

    //Initializing object of WebDriver
    WebDriver ldriver;

    //Constructor
    public signUpOrLoginPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver,this);
    }

    //Identifying WebElements
    @FindBy(id = "email_create")
    WebElement createEmailId;

    @FindBy(id = "SubmitCreate")
    WebElement submitCreate;

    @FindBy(id = "email")
    WebElement registeredEmailId;

    @FindBy(id = "passwd")
    WebElement registeredUserPass;

    @FindBy(id = "SubmitLogin")
    WebElement signIn;


    //Action on WebElements
    public void enterCreateEmailAddress(String emailId){
        createEmailId.sendKeys(emailId);
    }

    public void clickSubmitCreate(){
        submitCreate.click();
    }

    public void enterRegisteredEmailAddress(String emailId){
        registeredEmailId.sendKeys(emailId);
    }

    public void enterRegisteredPassword(String emailId){
        registeredUserPass.sendKeys(emailId);
    }

    public void clickSignInButton(){
        signIn.click();
    }

}
