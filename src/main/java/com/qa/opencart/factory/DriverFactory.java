package com.qa.opencart.factory;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager op;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger LOG=Logger.getLogger(DriverFactory.class);

	public WebDriver initDriver(Properties prop) {

		highlight = prop.getProperty("highlight");
		op = new OptionsManager(prop);

		String browserName = prop.getProperty("browser").toLowerCase();
		// browserName=browserName.toLowerCase();
		System.out.println("Browser name is:" + browserName);
		LOG.info("Browser name is:"+browserName);
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver=new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else if (browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			// driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			//System.out.println("Please pass the correct driver:" + browserName);
			LOG.error("Please pass the correct driver:" + browserName);
			throw new FrameworkException(AppError.BROWSER_ERROR_MESG);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("URL"));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/*
	 * This method is used to intialize the coonfig properties
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// commnd line args --> maven
		// mvn clean install -Denv="stage" -Dbrowser="chrome"
		// mvn clean install

		String envName = System.getProperty("env");
		// String envName = System.getenv("env");
		//System.out.println("Running test cases on environment: " + envName);
		LOG.info("Running test cases on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given...hence running it on QA env by default....");
			try {
				ip = new FileInputStream("./src/test/resource/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace(); 
			}

		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resource/config/config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;

				default:
					//System.out.println("Please pass the right environment.... " + envName);
					LOG.error("Please pass the right environment.... " + envName);
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	 /*
	  * to take screenshot
	  */
		public static String getScreenshot(String methodName) {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			//Users/naveenautomationlabs/Documents/workspace1/
			String path = System.getProperty("user.dir")+"/screenshot/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}
}