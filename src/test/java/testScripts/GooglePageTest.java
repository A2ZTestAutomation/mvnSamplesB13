package testScripts;



import java.util.concurrent.TimeUnit;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GooglePageTest {

	WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "F:\\Anandhi\\webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void searchJavaTest() {
		driver.get("https://www.google.co.in/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals("Java Tutorial - Google Search", driver.getTitle());
	}
	
	@Ignore
	@Test
	public void searchSeleniumTest() {
		driver.get("https://www.google.co.in/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
		Assert.assertEquals("Selenium Tutorial - Google Search", driver.getTitle());
		
	}
	
	@After
	public void teardown() {
		driver.close();
	}

}
