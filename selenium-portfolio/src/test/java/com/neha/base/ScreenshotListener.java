package com.neha.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.neha.utils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ScreenshotListener implements ITestListener {

    private static final ExtentReports extent = ExtentReportManager.getInstance();
    // ThreadLocal so parallel tests don't share the same ExtentTest instance
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName(),
                result.getMethod().getDescription());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Get driver from the failed test
        Object testInstance = result.getInstance();
        WebDriver driver = ((BaseTest) testInstance).driver;

        if (driver != null) {

            // 1. Save screenshot to /screenshots folder (original logic)
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                String fileName = result.getName() + "_" + timestamp + ".png";
                Path screenshotDir = Paths.get("screenshots");
                Files.createDirectories(screenshotDir);
                Path filePath = screenshotDir.resolve(fileName);
                Files.write(filePath, screenshot);
                System.out.println("Screenshot saved: " + filePath.toAbsolutePath());

                // 2. Attach screenshot inline to Extent Report
                String base64Screenshot = Base64.getEncoder()
                        .encodeToString(screenshot);
                test.fail("Screenshot on failure:",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(
                                base64Screenshot).build());

            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush(); // Writes everything to the HTML report
    }
}