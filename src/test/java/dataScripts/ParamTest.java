package dataScripts;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParamTest {
	
	WebDriver driver;
	@Parameters("browser")
	@BeforeMethod
	public void initBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
//	@Parameters({"uname", "pwd"})
  @Test(dataProvider="loginData")
  public void loginTest(String uname, String pwd) {
	  driver.get("https://the-internet.herokuapp.com/login");
	  driver.findElement(By.name("username")).sendKeys(uname);
	  driver.findElement(By.name("password")).sendKeys(pwd);
	  driver.findElement(By.xpath("//button[@class='radius']")).click();
	  boolean isloginSuccess = driver.findElement(By.xpath("//div[@class='flash success']")).isDisplayed();
	  Assert.assertTrue(isloginSuccess);
	  
  }
	
	//user1 - testuser1, welcome123
	//user2 - testuser2, welcome123
	//user3 - tomsmith,  SuperSecretPassword!
	//3,2
//	@DataProvider(name = "loginData")
//	public Object[][] getData(){
//		return new Object[][] {
//			new Object[] {"testuser1", "welcome123"},
//			new Object[] {"testuser2", "welcome123"},
//			new Object[] {"tomsmith", "SuperSecretPassword!"}
//		};
//	}
  
  //Read data from .csv file
//  @DataProvider(name = "loginData")
//	public Object[][] getData() throws CsvValidationException, IOException{
//	  String path = System.getProperty("user.dir")
//				+"//src//test//resources//testData//loginData.csv";
//	  CSVReader reader = new CSVReader(new FileReader(path));
//	  String[] col;
//	  ArrayList<Object[]> datalist = new ArrayList<Object[]>();
//	  while((col=reader.readNext())!=null) {
//		  Object[] record = {col[0], col[1]};
//		  datalist.add(record);
//		}
//	  reader.close();
//	  return datalist.toArray(new Object[datalist.size()][]);
//	  
//	}
//	
//Read data from .json file
  @DataProvider(name = "loginData")
	public String[][] getData() throws IOException, ParseException {
	  JSONParser parser = new JSONParser();
	  String path = System.getProperty("user.dir")
				+"//src//test//resources//testData//loginData.json";
	  FileReader reader= new FileReader(path);
	  Object obj = parser.parse(reader);
	  JSONObject jsonObj = (JSONObject)obj;
	  JSONArray userArray = (JSONArray)jsonObj.get("userlogins");
	  String arr[][] = new String[userArray.size()][];
	  for(int i = 0; i<userArray.size();i++) {
		  JSONObject user = (JSONObject) userArray.get(i);
		  String username = (String)user.get("username");
		  String password = (String)user.get("password");
		  String record[] = new String[2];
		  record[0] = username;
		  record[1] = password;
		  arr[i] = record;
	  }
	  
	  return arr;
  }
	@AfterMethod
	public void teardown() {
		driver.close();
	}
}
