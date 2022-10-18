package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	
	private By subscriptionYes=By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private By subscriptionNo=By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");

	private By policyAgree=By.name("agree");
	private By continueBtn=By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By regsSuccMesg=By.cssSelector("div#content h1");
	private By logoutBtn=By.linkText("Logout");
	private By registrationBtn=By.linkText("Register");
	
	
	public String userRegister(String firstName, String lastName, String email, String telephone,
			String password, String subscription) {
		eleUtil.doSendKeysWithWait(this.firstName, AppConstants.DEFAULT_LARGE_WAIT_TIME, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscription.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscriptionYes);
		}
		else {
			eleUtil.doClick(subscriptionNo);
		}
		
		eleUtil.doClick(policyAgree);
		eleUtil.doClick(continueBtn);
		
		String succMesg = eleUtil.waitForElementVisible(regsSuccMesg, AppConstants.DEFAULT_LARGE_WAIT_TIME).getText();
		System.out.println("Success message ===>"+succMesg);
		
		eleUtil.doClick(logoutBtn);
		eleUtil.doClick(registrationBtn);
		
		return succMesg;
		
	}
}
