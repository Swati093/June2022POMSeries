package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public SearchResultPage  searchResultPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	
	
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(String browser, String browserVersion, String testName) {
		
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browser!=null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		
		driver=df.initDriver(prop);
		loginPage=new LoginPage(driver);
		
	}
	
	@AfterTest
	public void tesrDown() {
		driver.quit();
	}

}
