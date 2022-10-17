package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	private By productImages = By.xpath("//ul[@class='thumbnails']/li");
	private Map<String, String> productInfoMap;
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public String getProductHeader(String mainProductName) {
		String xPath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xPath));
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_LARGE_TIME_OUT).size();
	}
	
	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitForTitleContains(AppConstants.DEFAULT_TIME_OUT, productTitle );
	}
	
	public String getProductPageURL(String searchKey) {
		return eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, searchKey);
	}
	
	public Map<String, String> getProductMetaData() {
		List<WebElement> metalist = eleUtil.getElements(productMetaData);
		productInfoMap = new LinkedHashMap<String, String>();
		for(WebElement e : metalist) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v) -> System.out.println(k+ ":" +v));
		return productInfoMap;
	}
	
	
	
	

}
