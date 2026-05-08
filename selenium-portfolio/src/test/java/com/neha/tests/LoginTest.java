package com.neha.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.neha.base.BaseTest;
import com.neha.pages.LoginPage;

 public class LoginTest extends BaseTest {
	 

	@Test(description="Valid login should lead to products page")
	public void testValidLogin(){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		loginPage.login("standard_user1", "secret_sauce1");
		
		String currentURL=driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("inventory"),"Expected to land on inventory page after login");		
	}
	

	@Test(description="Invalid login should show error message")
	public void testInvalidLogin(){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		loginPage.login("invalid_user", "invalid_Password");
		
		String error= loginPage.getErrorMessage();
		Assert.assertTrue(error.contains("Username and password do not match"), "Expected error message for invalid login");
	}
}
