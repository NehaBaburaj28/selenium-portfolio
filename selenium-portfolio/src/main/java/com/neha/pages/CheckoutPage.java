package com.neha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;

    private final By checkoutBtn      = By.cssSelector("[data-test='checkout']");
    private final By firstNameField   = By.id("first-name");
    private final By lastNameField    = By.id("last-name");
    private final By postalCodeField  = By.id("postal-code");
    private final By continueBtn      = By.cssSelector("[data-test='continue']");
    private final By finishBtn        = By.cssSelector("[data-test='finish']");
    private final By confirmationMsg  = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }

    public void fillShippingInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public void clickFinish() {
        driver.findElement(finishBtn).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMsg).getText();
    }
}