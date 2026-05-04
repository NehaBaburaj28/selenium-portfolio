package com.neha.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    private final WebDriver driver;

    private final By pageTitle       = By.cssSelector(".title");
    private final By cartBadge       = By.cssSelector(".shopping_cart_badge");
    private final By cartIcon        = By.cssSelector(".shopping_cart_link");
    private final By productNames    = By.cssSelector(".inventory_item_name");
    private final By inventoryItems  = By.cssSelector(".inventory_item");
    private final By addToCartBtn	 = By.cssSelector("button[data-test*='add-to-cart']");
    
    public ProductsPage(WebDriver driver) {
    	    	this.driver= driver;
    }
    
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
    
    public String getCartBadgeCount() {
    	return driver.findElement(cartBadge).getText();
    } 
    
    public void goToCart() {
    	driver.findElement(cartIcon).click();
    }
        
    /**
     * Clicks the Add to cart button for the product with the given visible name.
     * Waits up to 10s for the product list and button to be ready.
     * @throws NoSuchElementException if the product is not found
     */
    
    public void addProductToCart(String productName) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    	// Wait for product list to be visible
    	wait.until(ExpectedConditions.visibilityOfElementLocated(productNames));
    			  
    	driver.findElements(inventoryItems)
			  .stream()
			  .filter(item -> item.findElement(productNames).getText().equalsIgnoreCase(productName))
			  .findFirst()
			  .orElseThrow(() -> new NoSuchElementException("Product not found: " + productName))
			  .findElement(addToCartBtn)
			  .click();
    }
    	 
    public boolean isProductDisplayed(String productName) {
    	return driver.findElements(productNames)
    				 .stream()
    				 .anyMatch(el ->el.getText().equalsIgnoreCase(productName));
    }
}
