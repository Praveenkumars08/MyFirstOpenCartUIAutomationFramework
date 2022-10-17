package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private By fName = By.id("input-firstname");
	private By lName = By.id("input-lastname");
	private By eMail = By.id("input-email");
	private By telephoneNum = By.id("input-telephone");
	private By password = By.id("input-password");
	private By cPassword = By.id("input-confirm");
	
	private By subNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By subYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By privacyPolicyCheck = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By logOutLink = By.linkText("Logout");
	private By regAcct = By.linkText("Register");
	private By registerSuccessMesg = By.cssSelector("div#content h1");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}
	
	public String userRegister(String firstName, String lastName, String eMailId, String phNum, String pwd, String subscribe) {
		
		eleUtil.doSendKeysWithWait(fName, AppConstants.DEFAULT_TIME_OUT, firstName);
		eleUtil.doSendKeys(lName, lastName);
		eleUtil.doSendKeys(eMail, eMailId);
		eleUtil.doSendKeys(telephoneNum, phNum);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(cPassword, pwd);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subYes);
		}
		else {
			eleUtil.doClick(subNo);
		}
		
		eleUtil.doClick(privacyPolicyCheck);
		eleUtil.doClick(continueBtn);
		
		String succMesg =eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_TIME_OUT).getText();
		System.out.println("Success Message is :"+succMesg);
		eleUtil.doClick(logOutLink);
		eleUtil.doClick(regAcct);
		
		return succMesg;
	}
	

}
