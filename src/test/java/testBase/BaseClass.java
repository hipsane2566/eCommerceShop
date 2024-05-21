package testBase;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass implements ITestListener{
	protected static WebDriver driver;
	static Properties p;
	static Logger logging;
	public static ExtentSparkReporter sparkReport;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static  String path = System.getProperty("user.dir","\\reports");

	
	
	public static WebDriver intitializeBrowser(String browserName) throws IOException {
		p=new Properties();
		FileReader file = new FileReader("./src/test/resources/configure.properties");
		p.load(file);
			if(p.getProperty("os").equalsIgnoreCase("window")) {
//				switch(p.getProperty("browser")) 
				switch(browserName.toLowerCase()){
				case "chrome":
					//Setting up capabilities to run our test script
					ChromeOptions options = new ChromeOptions();
					
					//Add Adblocker extension to Chrome 
					options.addExtensions(new File("./src/test/resources/Adblock-all-advertisement-No-Ads-extension.crx"));
					options.addArguments("--disable-popup-blocking");
					options.addArguments("disable-infobars");
					// DesiredCapabilities capabilities = new DesiredCapabilities();
					//Using Chrome Options with Desired Capabilities Class
					// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					// capabilities.setPlatform(Platform.WIN11);
					// capabilities.setBrowserName("chrome");
					// options.merge(capabilities);
					driver = new ChromeDriver(options);
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
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	
	
	@BeforeSuite(groups="Regression")
	public void intialiseExtentReports() {
		sparkReport = new ExtentSparkReporter("AllTest.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReport);
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}
	
	@AfterSuite(groups="Regression")
	public void generateExtentReports() throws Exception {
		extentReports.flush();
		try {
			Desktop.getDesktop().browse(new File("AllTest.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Parameters("browserName")
	@BeforeClass(groups="Regression")
	public static void setup(@Optional("chrome") String browserName) throws IOException {
		driver = intitializeBrowser(browserName);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = "Regression")
	public static void teardown() {
		driver = getDriver();
		driver.quit();
	}
	
	@BeforeMethod(groups="Regression")
	public void getLog(ITestContext context){
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String device = capabilities.getBrowserName()+" "+capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
		String author = context.getCurrentXmlTest().getParameter("author");
		
		extentTest = extentReports.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);
	}
	
	@AfterMethod(groups="Regression")
	public void checkStatus(Method m, ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = null;
			screenshotPath = captureScreen(result.getTestContext().getName()+ "_" +result.getMethod().getMethodName()+".jpg");
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName());
		}
	}
	/*
	public static Logger log() {
		logging = LogManager.getLogger();
		return logging;
	}
	*/
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
