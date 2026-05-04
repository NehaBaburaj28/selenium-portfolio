package com.neha.tests;

import com.neha.base.BaseTest;
import com.neha.pages.CheckoutPage;
import com.neha.pages.LoginPage;
import com.neha.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void loginAndAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.goToCart();
    }

    @Test(description = "Completing checkout should show order confirmation")
    public void testFullCheckoutFlow() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();
        checkoutPage.fillShippingInfo("Harry", "Potter", "L4X 1A1");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        String confirmation = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(confirmation, "Thank you for your order!",
            "Order confirmation message should appear after checkout");
    }
}