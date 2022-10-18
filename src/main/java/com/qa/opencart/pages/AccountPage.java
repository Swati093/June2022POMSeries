package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logout=By.linkText("Logout");
	private By searchBox= By.name("search");
	private By secHeaderList=By.cssSelector("div#content h2");
	private By searchBtn=By.xpath("//button[@type='button']/i[contains(@class,'fa-search')]");
	
	
	//const..
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	public String getAccountPageTitle() {
		
		String actTitle = eleUtil.waitForTitleToBe(AppConstants.ACC_PAGE_TITLE, AppConstants.DEFAULT_WAIT_TIME);
		
		System.out.println("Account page title is:"+actTitle);
		return actTitle;
	}
	
	public boolean getAccountPageURL() {
		
		String url=eleUtil.waitForUrl(AppConstants.DEFAULT_WAIT_TIME, AppConstants.ACC_PAGE_URL_PARAM);
		
		System.out.println("Account page url is: "+url);
		if(url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	public boolean isSearchBoxExist() {
		return eleUtil.doIsDisplayed(searchBox);
	}
	
	public SearchResultPage performSearch(String productKey) {
		System.out.println("Product Name is:"+productKey);
		if(isSearchBoxExist()) {
			
			eleUtil.doSendKeys(searchBox, productKey);
			eleUtil.doClick(searchBtn);
			return new SearchResultPage(driver);
		}
		else {
			System.out.println("Search field does not exist on the page");
			return null;
		}
		
	}
	
	public boolean isLogoutExist() {
		return eleUtil.doIsDisplayed(logout);
	}
	
	public ArrayList<String> getAccSecHeaderList() {
		List<WebElement> secHeaList=eleUtil.waitForElementsToBeVisible(secHeaderList, AppConstants.DEFAULT_LARGE_WAIT_TIME);
		
		//List<WebElement> secHeaList=driver.findElements(secHeaderList);
		System.out.println("Total no.of header in Account page:"+secHeaList.size());
		ArrayList<String> actSecTextList=new ArrayList<String>();
		
		for(WebElement e : secHeaList) {
			String text=e.getText();
			actSecTextList.add(text);
		}
		return actSecTextList;
	}

}
