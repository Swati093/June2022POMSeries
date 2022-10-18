package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> metaMap;
	
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	

	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}

	
	public String getProductHeader(String mainProductName) {
		By prod_xpath=By.xpath("//h1[text()='"+mainProductName+"']");
		String productHeader=eleUtil.doElementGetText(prod_xpath);
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_LARGE_WAIT_TIME).size();
		}
	
	public String getProductPageTitle(String productName) {
		return eleUtil.waitForTitleToBe(productName, AppConstants.DEFAULT_WAIT_TIME);
	}
	
	public String getProductPageUrl(String searchKey) {
		return eleUtil.waitForTitleContains(searchKey, AppConstants.DEFAULT_LARGE_WAIT_TIME);
	}
	
	
	public Map<String, String> getProductMetaData() {
		
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		metaMap=new HashMap<String,String>();
		for( WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			metaMap.put(metaKey, metaValue);
			
			metaMap.forEach((k,v) -> System.out.println(k+":"+v)); 
		}
		return metaMap;
		
		
		
	}
}
