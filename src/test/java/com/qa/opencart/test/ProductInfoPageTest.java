package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("pwd"));

	}
	
	@DataProvider
	public Object[][] getSearchProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro" },
			{"macbook","MacBook Air"},
			{"iMac","iMac"},
			
		};
	}
	
	@Test(dataProvider="getSearchProductData")
	public void productPageHeadetTest(String searchKey, String searchProduct) {
		searchResultPage=accountPage.performSearch(searchKey);
		productInfoPage=searchResultPage.selectProduct(searchProduct);
		String actualProductHeader = productInfoPage.getProductHeader(searchProduct);
		Assert.assertEquals(actualProductHeader, searchProduct);
		
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro" , AppConstants.PROD_IMG_COUNT_MACBOOKPRO},
			{"macbook","MacBook Air",AppConstants.PROD_IMG_COUNT_MACBOOKAIR},
			{"iMac","iMac",AppConstants.PROD_IMG_COUNT_IMAC},
			
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void getProductImageCountTest(String searchKey , String mainProduct , int  imgCount) {
		searchResultPage=accountPage.performSearch(searchKey);
		productInfoPage=searchResultPage.selectProduct(mainProduct);
		int imageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(imageCount, imgCount);
	}
	
	@Test
	public void productMetaDataTest() {
		searchResultPage=accountPage.performSearch("Macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productMetaData = productInfoPage.getProductMetaData();
		Assert.assertEquals(productMetaData.get("Brand"), "Apple");
		Assert.assertEquals(productMetaData.get("Product Code"), "Product 18");
		Assert.assertEquals(productMetaData.get("Reward Points"), "800");
		Assert.assertEquals(productMetaData.get("Availability"), "In Stock");
	}

}
