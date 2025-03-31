package com.mystore.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener{

    ExtentSparkReporter  htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    public void configureReport() throws FileNotFoundException {
        ReadConfig readConfig = new ReadConfig();
        String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
        String reportName = "MyStoreTestReport-" + timestamp + ".html";
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        //add system information/environment info to reports
        reports.setSystemInfo("Machine:", "testpc1");
        reports.setSystemInfo("OS", "windows 11");
        reports.setSystemInfo("browser:", readConfig.getBrowser());
        reports.setSystemInfo("user name:", "Rishikesh");

        //configuration to change look and feel of report
        htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
        htmlReporter.config().setReportName("This is my First Report");
        htmlReporter.config().setTheme(Theme.DARK);


    }

    //OnStart method is called when any Test starts.
    public void onStart(ITestContext Result)
    {

        try {
            // Delete old reports before starting a new execution
            String reportDir = System.getProperty("user.dir") + "//Reports//";
            File reportFolder = new File(reportDir);

            if (reportFolder.exists() && reportFolder.isDirectory()) {
                File[] files = reportFolder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".html")) {
                            file.delete();
                            System.out.println("Deleted old report: " + file.getName());
                        }
                    }
                }
            }

            configureReport();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("On Start method invoked....");
    }

    //onFinish method is called after all Tests are executed
    public void onFinish(ITestContext Result)
    {
        System.out.println("On Finished method invoked....");
        reports.flush();//it is mandatory to call flush method to ensure information is written to the started reporter.

    }



    // When Test case get failed, this method is called.

    public void onTestFailure(ITestResult Result) {
        System.out.println("Name of test method failed: " + Result.getName());

        test = reports.createTest(Result.getName());  // Create entry in HTML report
        test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName(), ExtentColor.RED));

        String screenShotPath = System.getProperty("user.dir") + File.separator + "ScreenShots" + File.separator + Result.getName() + ".png";
        System.out.println("Screenshot Path: " + screenShotPath);

        File screenShotFile = new File(screenShotPath);

        if (screenShotFile.exists()) {
            System.out.println("Screenshot exists, adding to report.");
            test.fail("Captured Screenshot is below:");
            test.addScreenCaptureFromPath(screenShotFile.getAbsolutePath());
        } else {
            System.out.println("Screenshot not found!");
        }
    }


    // When Test case get Skipped, this method is called.

    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("Name of test method skipped:" + Result.getName() );

        test = reports.createTest(Result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName() ,ExtentColor.YELLOW));
    }

    // When Test case get Started, this method is called.

    public void onTestStart(ITestResult Result)
    {
        System.out.println("Name of test method started:" + Result.getName() );

    }

    // When Test case get passed, this method is called.

    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("Name of test method sucessfully executed:" + Result.getName() );

        test = reports.createTest(Result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " + Result.getName() ,ExtentColor.GREEN));
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }



}