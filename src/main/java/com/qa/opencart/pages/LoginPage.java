package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	 private WebDriver driver;
	 private ElementUtil eleUtil;
	
	//By locator
	private By emailId=By.id("input-email");
	private By passwordId=By.id("input-password");
	private By lgnBtn=By.xpath("//input[@value='Login']");
	private By forgotPass=By.linkText("Forgotten Password");
	private By register=By.linkText("Register");
	private By dummy=By.linkText("demmy");
	
	//page const..
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	//page actions
	public String getPageTitle() {
		
		String actTitle = eleUtil.waitForTitleToBe(AppConstants.LOG_PAGE_TITLE, AppConstants.DEFAULT_WAIT_TIME);
		
		
		//String title = driver.getTitle();
		System.out.println("Login page title is:"+actTitle);
		return actTitle;
	}
	
	public boolean getPageURL() {
		
		String url=eleUtil.waitForUrl(AppConstants.DEFAULT_WAIT_TIME, AppConstants.LOG_PAGE_URL_PARAM);
		
		//String url = driver.getCurrentUrl();
		System.out.println("Login page url is: "+url);
		if(url.contains(AppConstants.LOG_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	public boolean forgotPasswordLink() {
		return eleUtil.doIsDisplayed(forgotPass);
	}
	
	public AccountPage doLogin(String username,String pwd) {
		System.out.println("Usercredentials are:"+username+" "+pwd);
		
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_LARGE_WAIT_TIME, username);
		eleUtil.doSendKeys(passwordId, pwd);
		eleUtil.doClick(lgnBtn);
		
	
		return new AccountPage(driver);
		
	}
	
	public RegisterPage nevigateToRegisterPage() {
		System.out.println("Navigating to register page");
		eleUtil.doClick(register);
		return new RegisterPage(driver);
	}

}
