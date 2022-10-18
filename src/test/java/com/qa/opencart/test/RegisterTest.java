package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		registerPage=loginPage.nevigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	public String generateEmail() {
		Random random=new Random();
		String email="auto"+random.nextInt(100)+"sele"+random.nextInt(1000)+"@gmail.com";
		return email;
	}

	@Test(dataProvider="getRegisterData")
	public void registerUserTest(String firtsName, String lastName, String telephone, String password, String subscription) {
		String actSuccMesg = registerPage.userRegister( firtsName,  lastName,  generateEmail(),  telephone,  password,  subscription);
		Assert.assertEquals(actSuccMesg, AppConstants.ACC_CREATE_SUCC_MESG);
	}
}
