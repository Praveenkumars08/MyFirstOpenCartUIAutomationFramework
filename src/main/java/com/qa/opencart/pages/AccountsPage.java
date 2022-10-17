package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;


	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.xpath("//button[@type='button' and @class='btn btn-default btn-lg']");
	private By accSecHeaders = By.xpath("//h2");


	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_TITLE);
		System.out.println("Account page title is :"+title);
		return title;
	}

	public boolean getAccPageUrl() {
		String url =eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL_PARAM);
		System.out.println("Account page url is :"+url);
		if(url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	public boolean isLogoutLinkExist() {
		boolean flag =eleUtil.doEleIsDisplayed(logoutLink);
		return flag;
	}

	public boolean isSearchExist() {
		boolean flag = eleUtil.doEleIsDisplayed(search);
		return flag;
	}
	
	public SearchResultsPage performSearch(String productKey) {
		System.out.println("Product Name is :"+productKey);
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("Search field is not present on the page");
			return null;
		}
	}

	public ArrayList<String> getAccSecHeadersList() {
		
		List<WebElement> secList =eleUtil.waitForElementsToBeVisible(accSecHeaders, AppConstants.DEFAULT_TIME_OUT);
		
		System.out.println("Total section headers is :"+secList.size());
		ArrayList<String> actSecTextList = new ArrayList<>();
		for(WebElement e : secList) {
			String text = e.getText();
			actSecTextList.add(text);
		}
		return actSecTextList;
	}



}
