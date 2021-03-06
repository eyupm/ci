package AmazonTesting;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.*;
import utilities.*;

public class AmazonTestSystem {
	private RemoteWebDriver driver;
	private library lib;
	private testSetup tes;
	private androidHelper android;
	private home homePage;
	private searchResults searchResultsPage;
	private cartFunctions cart;

	@Parameters({ "targetEnvironment", "network", "networkLatency" })
	@BeforeTest
	public void beforeTest(String targetEnvironment, String network, String networkLatency)
			throws UnsupportedEncodingException, MalformedURLException {
		// initializes testSetup class
		tes = new testSetup(targetEnvironment, driver, network, networkLatency);
		// sets up the testNG flows based on testsuite.xml
		tes.flowControl();
	}

	public void setPagesAndHelpers(library lib)
			throws UnsupportedEncodingException, MalformedURLException {
		// initializes the androidHelper class
		android = new androidHelper(lib);
		// sets up the home page class
		homePage = new home(lib);
		// sets up the search results class
		searchResultsPage = new searchResults(lib);
		// sets up the cartFunctions class
		cart = new cartFunctions(lib);
		
		
	}

	@Test
	public void OrderBook() throws InterruptedException, IOException {
		// sets the RemoteWebDriver and initial library settings
		lib = tes.driverAndLibrarySetup();
		setPagesAndHelpers(lib);
		
		//sets Network and latency based on test parameters
		//network virtualization is per cloud and per device basis
		//i'm unable to reliably get this working
				if(lib.getNetwork().equals("4G"))
				{
					lib.startNetworkVirtualization("4g_lte_average", lib.getNetworkLatency());
				}
				

		// test start
		lib.log("orderBookStarted", false);
		try {
						
			lib.log("Going to amazon.com",false);
			lib.goToPage("http://amazon.com", "Amazon.com");
						
			lib.log("Checking if Chrome needs acceptance",false);
		    android.chromeFirstOpenAccepteance(60);
		                
			lib.log("Enter book into search box",false);
			homePage.searchBoxText("Army of darkness volume one", 60);
						
			lib.log("Searching for book", false);
			homePage.searchBoxSubmit(60);

			lib.log("Selecting book from results", false);
			searchResultsPage.selectResult("Army of Darkness Omnibus Volume 1",
					60);
						
			lib.log("Adding book to cart", false);
			cart.addToCart(60);

			lib.log("Proceeding to checkout", false);
			cart.proceedToCheckout(60);

		} catch (Exception ex) {
			throw ex;
		} finally {
			//downloading the perfecto report and cleaning up the drivers 
			if (lib.isDevice()) {
				lib.getDriver().close();
				lib.downloadReportDisplay(lib.getDriver(), true);
			} else {
				lib.getDriver().close();				
			}
			lib.getDriver().quit();
		}
	}
	
	@Test
	public void cleanUpCart() throws InterruptedException, IOException {
		// sets the RemoteWebDriver and initial library settings
		lib = tes.driverAndLibrarySetup();
		setPagesAndHelpers(lib);

		// test start
		lib.log("cleanCartStarted", false);
		try {

			lib.log("Going to amazon.com", false);
			lib.goToPage("http://amazon.com", "Amazon.com");
			
			lib.log("Checking if Chrome needs acceptance",false);
		    android.chromeFirstOpenAccepteance(60);
		    
		    lib.log("Clicking cart icon",false);
		    cart.clickCart();
		    
		    lib.log("Deleting all items from the cart",false);
		    cart.deleteFromCart();
			
		} catch (Exception ex) {			
			throw ex;
		} finally {
			//downloading the perfecto report and cleaning up the drivers 
			if (lib.isDevice()) {
				lib.getDriver().close();
				lib.downloadReportDisplay(lib.getDriver(), true);
			} else {
				lib.getDriver().close();				
			}
			lib.getDriver().quit();
		}
	}

	@AfterTest
	public void afterTest() throws IOException {

		//re-attempt driver clean up in case of massive failure
		try {
			lib.getDriver().close();
		} catch (Exception ex) {

		}
		
		try {
			lib.getDriver().quit();
		} catch (Exception ex) {

		}

		
	}

}
