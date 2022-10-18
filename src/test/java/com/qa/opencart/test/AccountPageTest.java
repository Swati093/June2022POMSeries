package com.qa.opencart.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("pwd"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		Assert.assertEquals(accountPage.getAccountPageTitle(),AppConstants.ACC_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageURLTest() {
		Assert.assertTrue(accountPage.getAccountPageURL());
	}
	
	@Test(priority = 3)
	public void isSearchBoxExist() {
		Assert.assertEquals(accountPage .isSearchBoxExist(), true);
	}
	
	@Test(priority = 4)
	public void logoutExistTest() {
		Assert.assertTrue(accountPage.isLogoutExist());
	}
		
	@Test(priority = 4)
	public void accSecHeaderTest() {
		ArrayList<String> heaList = accountPage.getAccSecHeaderList();
		System.out.println("Header Text is:"+heaList);
		Assert.assertEquals(heaList, AppConstants.ACC_PAGE_SEC_HEADER);
	}
	
	@DataProvider
	public Object[][] getProductKey() {
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"samsung"},
		};
		
	}
	
	@Test(dataProvider="getProductKey",priority = 6)
	public void searchCheckTest(String productKey) {
		searchResultPage=accountPage.performSearch(productKey);
		Assert.assertTrue(searchResultPage.isSearchSuccessful());
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"iMac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
		
	}
	
	@Test(dataProvider="getProductData",priority = 7)
	public void searchTest(String productKey,String mainProductName) {
		searchResultPage=accountPage.performSearch(productKey);
		if(searchResultPage.isSearchSuccessful()) {
			productInfoPage=searchResultPage.selectProduct(mainProductName);
			String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName); 
		}
	}
	
	
}
