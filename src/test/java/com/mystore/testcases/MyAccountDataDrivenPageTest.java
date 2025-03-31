package com.mystore.testcases;

import com.mystore.pageobject.accountCreationDetails;
import com.mystore.pageobject.indexPage;
import com.mystore.pageobject.myAccountPage;
import com.mystore.pageobject.signUpOrLoginPage;
import com.mystore.utilities.ReadExcelFile;
import junit.framework.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MyAccountDataDrivenPageTest extends BaseClass{

    public MyAccountDataDrivenPageTest() throws FileNotFoundException {

    }


    @Test(enabled = false)
    public void verifyRegistrationAndLogin(String userEmail, String password, String userFirstName, String userLastName){

        driver.get(url);
        logger.info("URL opened");
        indexPage indexPage = new indexPage(driver);
        signUpOrLoginPage signUp = new signUpOrLoginPage(driver);
        accountCreationDetails accCreation = new accountCreationDetails(driver);
        myAccountPage myAcc = new myAccountPage(driver);
        indexPage.clickOnSignIn();
        logger.info("SignUp/LogIn Page Opened.");
        signUp.enterCreateEmailAddress(userEmail);
        signUp.clickSubmitCreate();
        logger.info("Email submitted");
        accCreation.selectTitle();
        accCreation.customerFirstName(userFirstName);
        accCreation.customerLastName(userLastName);
        accCreation.customerPassword(password);
        accCreation.registerButton();
        Assert.assertEquals(myAcc.registeredToastMessage(),"Your account has been created.");
        logger.info("New User Registered!");

    }

    @Test(dataProvider = "LoginDataProvider")
    public void verifyRegisteredUserLogin(String userEmail, String password, String expectedUserName) throws IOException, InterruptedException {
        driver.get(url);
        logger.info("url opened");
        indexPage indexPage = new indexPage(driver);
        signUpOrLoginPage signUp = new signUpOrLoginPage(driver);
        myAccountPage myAcc = new myAccountPage(driver);
        indexPage.clickOnSignIn();
        signUp.enterRegisteredEmailAddress(userEmail);
        logger.info("Email ID entered");
        signUp.enterRegisteredPassword(password);
        logger.info("Password entered");
        signUp.clickSignInButton();
        logger.info("Clicked on Sign In button.");
        String userName = myAcc.registeredUserName();
        if(userName.equals(expectedUserName)) {
            logger.info("Logged In Successfully");
            Assert.assertTrue(true);
            myAcc.signOut();
            Thread.sleep(3000);
            logger.info("Logged Out successfully");

        } else{
            logger.info("Logged In Unsuccessfully");
            captureScreenShot(driver,"verifyRegisteredUserLogin");
            Assert.assertTrue(false);
        }

    }

    @DataProvider(name = "LoginDataProvider")
    public String[][] LoginDataProvider()
    {
        //System.out.println(System.getProperty("user.dir"));
        String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";


        int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
        int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");


        String[][] data =new String[ttlRows-1][ttlColumns];

        for(int i=1;i<ttlRows;i++)//rows =1,2
        {
            for(int j=0;j<ttlColumns;j++)//col=0, 1,2
            {

                data[i-1][j]=ReadExcelFile.getCellValue(fileName,"LoginTestData", i,j);
            }

        }
        return data;
    }
}
