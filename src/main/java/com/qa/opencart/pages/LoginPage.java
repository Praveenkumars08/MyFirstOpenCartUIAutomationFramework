package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	//If it's not private then any class can create the object of LoginPage class, they might get null reference
	//1. By Locator
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logoImage = By.xpath("//img[@title='naveenopencart']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By regAcctLink = By.linkText("Register");

	//2. Page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}

	//3. Page actions
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("login page title : " + title);
		return title;
	}

	public boolean getLoginPageUrl() {
		String url =eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("login page url : " + url);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.doEleIsDisplayed(forgotPwdLink);
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user creds are : " + username + " : "+ pwd);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_TIME_OUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		System.out.println("Navigating to register page");
		eleUtil.doClick(regAcctLink);
		return  new RegisterPage(driver);
	}



}
