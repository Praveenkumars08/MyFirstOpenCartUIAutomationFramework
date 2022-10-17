package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
		
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object[][] regData = ExcelUtil.getTestData("register");
		return regData;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String fName, String lName, String email, String phNum, String pwd, String subscribe) {
		String actualSuccMsg = registerPage.userRegister(fName, lName, email, phNum, pwd, subscribe);
		Assert.assertEquals(actualSuccMsg, AppConstants.SUCCESS_MSG_REGISTER);
	}

}
