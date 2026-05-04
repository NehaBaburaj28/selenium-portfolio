package com.neha.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.neha.base.BaseTest;
import com.neha.pages.LoginPage;
import com.neha.pages.ProductsPage;

public class ProductsTest extends BaseTest {

	@BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

	@Test(description = "Products page should load with correct title")
    public void testProductsPageLoads() {
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
            "Products page title should be 'Products'");
    }

    @Test(description = "Adding item to cart should update cart badge")
    public void testAddToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "2",
            "Cart badge should show 2 after adding one item");
    }

    @Test(description = "Specific product should be visible on the page")
    public void testProductIsDisplayed() {
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductDisplayed("Sauce Labs Backpack"),
            "Sauce Labs Backpack should be visible on the products page");
    }
}
