package test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.mobile.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Page00 {
	

	public WebDriver driver;
	WebDriver wait;
	
	public Page00 (WebDriver driver){
		this.driver = driver;
	}
	
	public void wf_loginPageMobile() {

		System.out.println("### wf_loginPageMobile ###");
   
		driver.get("http://wf.com");
		//waitForTitle(10, "Mobile Banking");
		sleep(100);
		//driver.findElement(By.xpath("//a[@id='load-login']")).click();

		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys("apmbillpay");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("apmbillpay2014");
		driver.findElement(By.xpath("//BUTTON[text()='Sign On']")).click();

		waitForTitle(10, "Sign Off");
		driver.findElement(By.xpath("//a[@id='sign-off']")).click();
		sleep(200);
		takeScreenshot();

	}

	
	public void wf_loginPage() {

		System.out.println("### wf_loginPage ###");
   
		driver.get("http://wf.com");
		waitForTitle(10, "Mobile Banking");
		sleep(100);
		driver.findElement(By.xpath("//a[@id='load-login']")).click();

		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys("apmbillpay");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("apmbillpay2014");
		driver.findElement(By.xpath("//BUTTON[text()='Sign On']")).click();

		waitForTitle(10, "Sign Off");
		driver.findElement(By.xpath("//a[@id='sign-off']")).click();

		takeScreenshot();

	}

	
    @Attachment
    public byte[] takeScreenshot() {
        System.out.println("Taking screenshot heheha");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

	public void waitForTitle(int seconds, String title) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception ex) {

		}
	}
	

	private static void sleep (long millis) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {}
	}
	
	
}
