package testScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SampleTwoTest {
	WebDriver driver;
  @Test
  public void searchCypressTest() {
	  System.setProperty("webdriver.chrome.driver", "F:\\Anandhi\\webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in/");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(), "Google");
//		Assert.assertEquals(driver.getTitle(), "Google");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cypress Tutorial");
		searchBox.submit();
		softAssert.assertEquals(driver.getTitle(), "Cypress Tutorial - Google Search");
//		softAssert.assertAll();
		driver.close();
		softAssert.assertAll();
  }
}
