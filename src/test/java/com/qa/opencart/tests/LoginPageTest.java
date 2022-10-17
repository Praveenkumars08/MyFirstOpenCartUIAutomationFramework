package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;

public class LoginPageTest extends BaseTest {
	
	@Description
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		boolean flag = loginPage.getLoginPageUrl();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void isForgotPwdLinkExistTest() {
		boolean flag = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean flag = accPage.isLogoutLinkExist();
		Assert.assertTrue(flag);
	}
	
	

}
