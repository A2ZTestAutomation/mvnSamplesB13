package dataScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropReadTest {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void initBrowser() throws IOException {
		prop = new Properties();
		String path = System.getProperty("user.dir")
				+"//src//test//resources//testData//config.properties";
		FileInputStream fin = new FileInputStream(path);
		prop.load(fin);
		String browser = prop.getProperty("browser");
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
  @Test
  public void loginTest() throws ParserConfigurationException, SAXException, IOException {
	  driver.get(prop.getProperty("url"));
	  driver.findElement(By.name(readXmlData("uname"))).sendKeys(prop.getProperty("username"));
	  driver.findElement(By.name(readXmlData("pwd"))).sendKeys(prop.getProperty("password"));
	  driver.findElement(By.xpath(readXmlData("loginBtn"))).click();
	  boolean isloginSuccess = driver.findElement(By.xpath("//div[@class='flash success']")).isDisplayed();
	  Assert.assertTrue(isloginSuccess);
  }
  
  public String readXmlData(String tagname) throws ParserConfigurationException, SAXException, IOException {
	  String path = System.getProperty("user.dir")
				+"//src//test//resources//testData//XmlObjRepo.xml";
	  File file = new File(path);
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder build = factory.newDocumentBuilder();
	  Document document = build.parse(file);
	  NodeList list = document.getElementsByTagName("objRep");
	  Node node1=list.item(0);
	  Element elem = (Element)node1;
	  return elem.getElementsByTagName(tagname).item(0).getTextContent();
	  
  }
}
