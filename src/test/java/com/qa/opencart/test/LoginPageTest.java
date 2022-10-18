package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest{

	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		String actualTitle=loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOG_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void pageURLTest() {
		Assert.assertTrue(loginPage.getPageURL());
	}
	
	@Test(priority = 3)
	public void isForgotPasswordLinkExist() {
		Assert.assertEquals(loginPage.forgotPasswordLink(), true);
	}
	
	@Test(priority = 4)
	public void loginTest() {
		accountPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("pwd"));
		Assert.assertTrue(accountPage.isLogoutExist());
	}
}
