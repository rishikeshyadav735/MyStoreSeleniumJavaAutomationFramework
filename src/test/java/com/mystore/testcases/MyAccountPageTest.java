package com.mystore.testcases;

import com.mystore.pageobject.accountCreationDetails;
import com.mystore.pageobject.indexPage;
import com.mystore.pageobject.myAccountPage;
import com.mystore.pageobject.signUpOrLoginPage;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MyAccountPageTest extends BaseClass{

    public MyAccountPageTest() throws FileNotFoundException {

    }

    @Test
    public void verifyRegistrationAndLogin(){

        driver.get(url);
        logger.info("URL opened");
        indexPage indexPage = new indexPage(driver);
        signUpOrLoginPage signUp = new signUpOrLoginPage(driver);
        accountCreationDetails accCreation = new accountCreationDetails(driver);
        myAccountPage myAcc = new myAccountPage(driver);
        indexPage.clickOnSignIn();
        logger.info("SignUp/LogIn Page Opened.");
        signUp.enterCreateEmailAddress("rishikeshy11@gmail.com");
        signUp.clickSubmitCreate();
        logger.info("Email submitted");
        accCreation.selectTitle();
        accCreation.customerFirstName("Rishikesh");
        accCreation.customerLastName("Yadav");
        accCreation.customerPassword("pass1234");
        accCreation.registerButton();
        Assert.assertEquals(myAcc.registeredToastMessage(),"Your account has been created.");
        logger.info("New User Registered!");

    }

    @Test(enabled = false)
    public void verifyRegisteredUserLogin() throws IOException {
        driver.get(url);
        logger.info("url opened");
        indexPage indexPage = new indexPage(driver);
        signUpOrLoginPage signUp = new signUpOrLoginPage(driver);
        accountCreationDetails accCreation = new accountCreationDetails(driver);
        myAccountPage myAcc = new myAccountPage(driver);
        indexPage.clickOnSignIn();
        signUp.enterRegisteredEmailAddress("rishikeshy2@gmail.com");
        logger.info("Email ID entered");
        signUp.enterRegisteredPassword("11223344");
        logger.info("Password entered");
        signUp.clickSignInButton();
        logger.info("Clicked on Sign In button.");
        String userName = myAcc.registeredUserName();
        if(userName.equals("Rishikesh Yadav")) {
            logger.info("Logged In Successfully");
            Assert.assertTrue(true);

        } else{
            logger.info("Logged In Unsuccessfully");
            captureScreenShot(driver,"verifyRegisteredUserLogin");
            Assert.assertTrue(false);
        }

    }
}
