package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACC_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageUrlTest() {
		boolean flag = accPage.getAccPageUrl();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void searchExistTest() {
		boolean flag = accPage.isSearchExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		boolean flag = accPage.isLogoutLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 5)
	public void accPageHeadersTest() {
		ArrayList<String> actHeaderList = accPage.getAccSecHeadersList();
		System.out.println("Actual account header list is :"+ actHeaderList);
		Assert.assertEquals(actHeaderList, AppConstants.ACC_PAGE_SEC_HEADERS);
	}
	
	@DataProvider
	public Object getProductKey() {
		return new Object[][] {
			{"Macbook"},
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
		};
	}
	
	
	@Test( dataProvider = "getProductKey", priority = 6)
	public void searchCheckTest(String productKey) {
		searchResultPage = accPage.performSearch(productKey);
		boolean flag = searchResultPage.isSearchSuccessful();
		Assert.assertTrue(flag);
	}
	@DataProvider
	public Object getProductData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test( dataProvider = "getProductData", priority  = 7)
	public void searchTest(String searchKey, String mainProductName) {
		searchResultPage = accPage.performSearch(searchKey);
		if(searchResultPage.isSearchSuccessful()) {
			productInfoPage = searchResultPage.selectProduct(mainProductName);
			String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
		}
	}

}
