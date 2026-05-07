package com.neha.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		
		Object testInstance = result.getInstance();
		WebDriver driver = ((BaseTest)testInstance).driver;
		
		if(driver!=null) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				
				//Build filename: TestName_2026-05-07_14-30-00.png
				String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
				String testName = result.getName();
				String fileName = testName + "_" + timestamp + ".png";
				
				// Save to /screenshots folder in project root
				Path screenshotDir = Paths.get("screenshots");
				Files.createDirectories(screenshotDir);
				Path filePath = screenshotDir.resolve(fileName);
				Files.write(filePath, screenshot);
				
				System.out.println("Screenshot saved:" + filePath.toAbsolutePath());
			
			} catch (IOException e) {
				System.err.println("Failed to save screenshot:" + e.getMessage());
			}
		}
	}
}
