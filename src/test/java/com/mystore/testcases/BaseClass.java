package com.mystore.testcases;

import com.mystore.utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {

    ReadConfig readConfig = new ReadConfig();

    public static WebDriver driver;
    public static Logger logger = LogManager.getLogger(BaseClass.class);

    String url = readConfig.getBaseUrl();
    String browser = readConfig.getBrowser();

    public String emailAddress = readConfig.getEmail() ;
    String password = readConfig.getPassword();

    public BaseClass() throws FileNotFoundException {

    }



    @BeforeMethod
    public void setup(){

        switch(browser.toLowerCase()){

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-web-security");
                options.addArguments("--disable-site-isolation-trials");
                driver = new ChromeDriver(options);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                driver = null;
                break;
        }

        //Implicit wait of 10 sec
        assert driver != null;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //Logging implementation
        logger = LogManager.getLogger("MyStoreV1");
        logger.info("Browser setup completed");

    }


    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    //user method to capture screenshot
    public void captureScreenShot(WebDriver driver,String testName) throws IOException {
        //step1: convert webdriver object to TakesScreenshot interface
        TakesScreenshot screenshot = ((TakesScreenshot)driver);

        //step2: call getScreenshotAs method to create image file

        File src = screenshot.getScreenshotAs(OutputType.FILE);

        File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");

        //step3: copy image file to destination
        FileUtils.copyFile(src, dest);
    }

}
