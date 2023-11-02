package org.appi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.android.AndroidDriver;

public class Calculator {
	
	public static AndroidDriver driver;
	ExtentHtmlReporter htmlrep;
	ExtentReports ext;
	ExtentTest test;
	
	@BeforeClass
	private void appLaunch() throws IOException {
		
		htmlrep = new ExtentHtmlReporter("report.html");
		ext = new ExtentReports();
		ext.attachReporter(htmlrep);
	
		test = ext.createTest("Android", "Calculator");
		DesiredCapabilities cap = new DesiredCapabilities();
		
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\loges\\eclipse-workspace\\AppiumTest\\Files\\config.properties");
		pro.load(fis);
		
		cap.setCapability("platformName", pro.getProperty("platformName"));
		cap.setCapability("platformVersion", pro.getProperty("platformVersion"));
		cap.setCapability("deviceName", pro.getProperty("deviceName"));
		cap.setCapability("udid", pro.getProperty("udid"));
		cap.setCapability("appPackage", pro.getProperty("appPackage"));
		cap.setCapability("appActivity", pro.getProperty("appActivity"));

		
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver(url, cap);
		
		
		test.log(Status.INFO, "press 8");
		WebElement f1 = driver.findElement(By.id(pro.getProperty("f1")));
		f1.click();
		test.log(Status.INFO, "press 9");
		WebElement f2 = driver.findElement(By.id(pro.getProperty("f2")));
		f2.click();
		test.log(Status.PASS, "press multiply");
		WebElement f3 = driver.findElement(By.id(pro.getProperty("f3")));
		f3.click();
		test.log(Status.INFO, "press 7");
		WebElement f4 = driver.findElement(By.id(pro.getProperty("f4")));
		f4.click();
		test.log(Status.INFO, "press 5");
		WebElement f5 = driver.findElement(By.id(pro.getProperty("f5")));
		f5.click();
		test.log(Status.PASS, "press equal");
		WebElement f6 = driver.findElement(By.id(pro.getProperty("f6")));
		f6.click();
		
		TakesScreenshot t = (TakesScreenshot)driver;
		File i = t.getScreenshotAs(OutputType.FILE);
		File img = new File("./screenshot\\scr.png");
		FileUtils.copyFile(i, img);
		
		
	}
	
	@BeforeMethod
	private void startTime() {
		Date d = new Date();
		System.out.println(d);
	}
	@Test(priority = 1)
	private void tc1() {
		System.out.println("1");
		
	}
	@Test(priority = 2)
	private void tc2() {
		System.out.println("2");
		
	}

	
	
	@AfterMethod
	private void endTime() {
		Date d = new Date();
		System.out.println(d);
	}
	@AfterClass
	private void appClose() {
		test.log(Status.INFO, "close the app");
		ext.flush();
		driver.quit();
	}

}
