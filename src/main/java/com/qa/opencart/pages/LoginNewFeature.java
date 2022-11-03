package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class LoginNewFeature {
	
	String newBtn="Cntinue";
	
	By btn=By.id(newBtn);
	
	public void clickNewBtnBy() {
		System.out.println("clicking btn"+btn);
	}

}
