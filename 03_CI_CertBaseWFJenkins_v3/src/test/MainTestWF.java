//test git2U test

package test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Attachment;
import test.Pages.*;


@Test
public class MainTestWF {

	private static final String zipCode = null;
	public  String SELENIUM_HUB_URL;
	public  String TARGET_SERVER_URL;
	public  WebDriver driver;
	public WebDriverWait wait; 
	public boolean device; 

	
	@Parameters({ "targetEnvironment" })
	
	//- collection of test device and browser environment
	
	@BeforeTest
	public void beforeTest(String targetEnvironment) throws UnsupportedEncodingException, MalformedURLException {
	
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (targetEnvironment) {
		
		
		case "Android HTC":
			device = true;
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "HT34HW904064");
			capabilities.setCapability("browserName", "mobileChrome");
			
			break;
		
		case "Android Galaxy Tab 2":
			device = true;
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "F818D88E");
			capabilities.setCapability("browserName", "mobileChrome");
			break;
			

		case "iOS 5S":
			device = true;
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "0E6FBDEEF6C5EECEF71F66904FC4EFDA3F803682");
			capabilities.setCapability("browserName", "mobileSafari");
			break;
			
		case "iPad Mini 2":
			device = true;
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "601E806DA24F7E846BCF7F41ACEFFA70425EEDE4");
			capabilities.setCapability("browserName", "mobileSafari");
			break;
	
		case "Internet Explorer 11":
			device = false;
			DesiredCapabilities.internetExplorer();
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "internet explorer");
			capabilities.setCapability("version", "11");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
			break;

		case "Internet Explorer 10":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "internet explorer");
			capabilities.setCapability("version", "10");
			break;	

			
		case "Firefox 34":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "firefox");
			capabilities.setCapability("version", "34.0");
			break;

		case "Firefox 38":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "firefox");
			capabilities.setCapability("version", "38");
			break;

		case "Chrome":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("version", "");
			break;
		}
		
		//- Input Data for Grid, Web Application, and Mobile Cloud Login info
			
		TARGET_SERVER_URL = getConfigurationProperty("TARGET_SERVER_URL",
				"test.target.server.url", "http://wf.com/");

		String user = "eugene.yu@ymcsolutions.com";
		String password = "ymcDemo321!";
		String host = "partners.perfectomobile.com";
		
		if (device) {

			System.out.println(targetEnvironment + ": device");
			
			user = URLEncoder.encode(user,"UTF-8");
			password = URLEncoder.encode(password, "UTF-8");
			URL gridURL = new URL("https://" + user + ':' + password + '@'
					+ host + "/nexperience/wd/hub");

			SELENIUM_HUB_URL = getConfigurationProperty("SELENIUM_HUB_URL",
					"test.selenium.hub.url", gridURL.toString());

		} else {
			System.out.println(targetEnvironment + ": desktop");;
			SELENIUM_HUB_URL = getConfigurationProperty("SELENIUM_HUB_URL",
					"test.selenium.hub.url",
					//"http://localhost:4444/wd/hub");
					"http://64.18.206.177:4444/wd/hub"); //-iLand
					//"http://seleniumgrid.perfectomobilelab.net:4444/wd/hub"); //-David Cloud

				
		}
		
		
		driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), capabilities);

		//  test starts in Codes entity list page
		driver.get(TARGET_SERVER_URL);
		System.out.println(SELENIUM_HUB_URL + " " + capabilities.getPlatform());
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, 20);
	}

	
    
	//-- Call Sub Test Page Page or Module 
	
    public void test00_wf() {
		Page00 Page00 = new Page00(driver); // Create Instant	

		if (device) {
			Page00.wf_loginPageMobile(); 
		} else {
			Page00.wf_loginPage();
		}
        
	}	
    
    
    public void test11_wf() {
		Page11 Page11 = new Page11(driver); // Create Instant		
		if (device) {
			Page11.wf_testStudentLoansMobile(); 
		} else {
			Page11.wf_testStudentLoans();
		}
		
	}	
    
	public void test22_wf() {	
		Page22 Page22 = new Page22(driver);
		
		if (device) {
			Page22.wf_testSiteMenuMobile();
		} else {
			Page22.wf_testSiteMenu();
		}
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='options']"))); //Verify Page
		//Page22.wf_testSiteMenu();	
	} 
	
	
	//- After Test Done Generate HTML and Screenshot Reports
	
	public void testTearDown() throws Exception {
		if (driver != null) {
			driver.close();
			
			if (device) { downloadReport("html"); 
			
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
			//String destDir = "./reports/screenshots/";
			String destDir = "./surefire-reports/html/screenshots/";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date());

			
			downloadReportIMG("pdf", destDir + "/" + destFile);
			
			downloadReportDisplay(getDriver(), true);//-CopyAmazon: add downloadReportPM
			
			
			//Display screenshot to ReportNG
		    final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
		    System.setProperty(ESCAPE_PROPERTY, "false");
		    
			//String userDirector = System.getProperty("user.dir") + "/reports/screenshots/"; 
		    String userDirector = System.getProperty("user.dir") + "/surefire-reports/html/screenshots/"; 
		    //String userDirector = "./screenshots/"; 
		    
			System.out.println(userDirector);
			String destFileNew = destFile + ".pdf";
			

	        Reporter.log("<a href=\"" + userDirector + destFileNew + "\">Perfecto Report</a><br />");             

	        //System.out.println("test data v3");
	        //System.out.println(userDirector);
	        //System.out.println(destFileNew);
	        //System.out.println(destDir);
	        
	        
			}  
			
			driver.quit();	
		}
	}

	//- Reusable Tool Modules
	
	@Attachment
	
	private byte[] downloadReport(String type) throws IOException
	{	
		String command = "mobile:report:download";
		Map<String, String> params = new HashMap<>();
		params.put("type", type);
		String report = (String)((RemoteWebDriver) driver).executeScript(command, params);
		byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
		return reportBytes;
	}
	
	
	
	// -Copy from Amazon: download report from perfecto
	private void downloadReportPM (RemoteWebDriver driver, String type,
			String fileName) throws IOException {
		// downloads report from perfecto
		String command = "mobile:report:download";
		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		String report = (String) driver.executeScript(command, params);
		File reportFile = new File(fileName + "." + type);
		BufferedOutputStream output = new BufferedOutputStream(
				new FileOutputStream(reportFile));
		byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
		output.write(reportBytes);
		output.close();

	}
	
	//- for report with screen attachment
	  private void downloadReportIMG(String type, String fileName) throws IOException {
			try { 
				String command = "mobile:report:download"; 
				Map<String, Object> params = new HashMap<>(); 
				params.put("type", type); 
				String report = (String)((RemoteWebDriver) driver).executeScript(command, params); 
				File reportFile = new File(fileName + "." + type); 
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile)); 
				byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report); 
				output.write(reportBytes); output.close(); 
			} catch (Exception ex) { 
				System.out.println("Got exception " + ex); }
			}
	
		// - Copy from Amazon: Calls downloadreport, copys the perfecto report to the screen directory
		// returns the driver
		public RemoteWebDriver getDriver() {
			return (RemoteWebDriver) driver;
		}
	  
		// - Copy from Amazon: Calls downloadreport, copys the perfecto report to the screen directory
		// boolean will add the report to the testNG report
		public void downloadReportDisplay(RemoteWebDriver driver, Boolean addReport)
				throws IOException {

			// set file format and destination for report
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "./surefire-reports/html/screenshots/";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date());

			// download report from driver
			downloadReportPM(driver, "pdf", destDir + "/" + destFile);

			// Display screenshot to ReportNG
			String userDirector = "./screenshots/";

			String destFileNew = destFile + ".pdf";
			
			
			/*
			log("perfectoReport: " + userDirector + destFileNew, false);
			if (addReport) {
				log("<a href=\"" + userDirector + destFileNew
						+ "\">Perfecto Report</a><br />", addReport);
						
			}*/
		}

		
	
	private static String getConfigurationProperty(String envKey,
			String sysKey, String defValue) {
		String retValue = defValue;
		String envValue = System.getenv(envKey);
		String sysValue = System.getProperty(sysKey);
		// system property prevails over environment variable
		if (sysValue != null) {
			retValue = sysValue;
		} else if (envValue != null) {
			retValue = envValue;
		}
		return retValue;
	}
	
	@AfterTest
	public void closeWebDriver () throws SessionNotFoundException {
		// make sure web driver is closed
		try{
			if ( ((RemoteWebDriver) driver).getSessionId() != null) {
				driver.close();			
				driver.quit();
			}	
		}
		catch (SessionNotFoundException e) {}
			
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	

	@BeforeMethod
	public void setUp() throws Exception {
	}

	@AfterMethod
	public void tearDown() throws Exception {
	}

}
