package test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.mobile.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Attachment;



import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.SessionNotFoundException;







import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Page11 {
	
	public static WebDriver driver;
	WebDriver wait;
	
	public boolean statusOK;


	
	
	public Page11 (WebDriver driver){
		this.driver = driver;
	}
	
	public void wf_testStudentLoansMobile(){

		System.out.println("### wf_testStudentLoansMobile ###");

		driver.get("http://wf.com");
		//waitForTitle(10, "Mobile Banking");
		sleep(100);
		//driver.findElement(By.xpath("//a[@id='load-login']")).click();
        
		driver.findElement(By.linkText("Student Loans")).click();
		
		waitForTitle(10, "Ready to apply");
		takeScreenshot();

	}
	
	public void wf_testStudentLoans(){

		System.out.println("### wf_testStudentLoans ###");

		driver.get("http://wf.com");
		//waitForTitle(10, "Mobile Banking");
		sleep(100);
		//driver.findElement(By.xpath("//a[@id='load-login']")).click();
		driver.findElement(By.linkText("Student Loans")).click();

		waitForTitle(10, "Ready to apply");
		takeScreenshot();

	}

	

	
    @Attachment
    public static byte[] takeScreenshot() {
        System.out.println("Taking screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
	

	private static void sleep (long millis) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {}
	}

	
	/*
	public Boolean IsTextPresent(String textToBeVerified)
	{

	try
	{
		if (driver.findElement(By.linkText(textToBeVerified)) != null)
	
	{
	return true;
	}
	} catch (Exception)
	{
	return false;
	}

	return false;
	}
    */


	public void waitForTitle(int seconds, String title) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception ex) {

		}
	}
	
	
}
