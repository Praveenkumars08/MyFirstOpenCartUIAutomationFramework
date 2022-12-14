package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	private By productSearchLayout = By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']");

	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public boolean isSearchSuccessful() {
		List<WebElement> searchList =
				eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_TIME_OUT);
		if(searchList.size() > 0) {
			System.out.println("Search is successful");
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		By mainPrName = By.linkText(mainProductName);
		eleUtil.doClick(mainPrName);
		return new ProductInfoPage(driver);
	}

}
