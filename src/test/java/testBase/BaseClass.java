package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class BaseClass {
	protected static WebDriver driver;
	static Properties p;
	static Logger logging;

	public static WebDriver intitializeBrowser() throws IOException {
		p=new Properties();
		FileReader file = new FileReader("./src/test/resources/configure.properties");
		p.load(file);
		
			if(p.getProperty("os").equalsIgnoreCase("window")) {
				switch(p.getProperty("browser")) {
				case "chrome":
					driver = new ChromeDriver();
					break;
				case "edge":
					driver = new EdgeDriver();
					break;
				default:
					driver = null;
				}
		}
		driver.get(p.getProperty("Appurl"));
		driver.manage().deleteAllCookies();
		log();
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	@BeforeClass
	public static void setup() throws IOException {
		driver = intitializeBrowser();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	// @AfterClass
	public static void teardown() {
		driver = getDriver();
		driver.quit();
	}
	
	public static Logger log() {
		logging = LogManager.getLogger();
		return logging;
	}
}
