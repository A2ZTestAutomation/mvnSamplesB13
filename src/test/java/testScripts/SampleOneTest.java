package testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonUtil.Utility;

public class SampleOneTest {
	WebDriver driver;
	ExtentTest extentTest;
	ExtentReports reports;
	ExtentHtmlReporter htmlReport;
	@BeforeTest
	public void setExtent() {
		reports = new ExtentReports();
		htmlReport = new ExtentHtmlReporter("F:\\Screenshot\\ExtentReportB13.html");
		reports.attachReporter(htmlReport);
	}
	@BeforeMethod
	public void setup() {
		 System.setProperty("webdriver.chrome.driver", "F:\\Anandhi\\webdrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
	}
	@Test
  public void searchJavaTest() {
		extentTest = reports.createTest("Search Java Test");
		driver.get("https://www.google.co.in/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals("Java Tutorial - Google Search", driver.getTitle());
  }

  @Test(retryAnalyzer = RetryAnalyzerTest.class)
  public void searchSeleniumTest() {
	  extentTest = reports.createTest("Search Selenium Test");
	  driver.get("https://www.google.co.in/");
	  Assert.assertEquals("Goog", driver.getTitle());
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
		Assert.assertEquals("Selenium Tutorial - Google Search", driver.getTitle());
	 }
 
  @AfterMethod
  public void teardown(ITestResult result) throws IOException {
	  if(ITestResult.FAILURE == result.getStatus()) {
		  String path = Utility.getScreenshotPath(driver);
		  extentTest.fail(result.getName());
		  extentTest.fail(result.getThrowable().getMessage(), 
				  MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	  }
	  if(ITestResult.SKIP == result.getStatus()) {
		  extentTest.skip(result.getName());
	  }
	  
	  driver.close();
  }
  @AfterTest
  public void finishReport() {
	  reports.flush();
  }
}
