package com.praktikum.automation.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    @FindBy(linkText = "Add to cart")
    private WebElement addToCartButton;

    @FindBy(css = ".name")
    private WebElement productName;

    @FindBy(css = ".price-container")
    private WebElement productPrice;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickProduct(String productName) {
        WebElement product = driver.findElement(By.linkText(productName));
        clickElement(product);
    }

    public void addToCart() {
        clickElement(addToCartButton);
    }

    public String getProductName() {
        waitForElement(productName);
        return productName.getText();
    }

    public String getProductPrice() {
        waitForElement(productPrice);
        return productPrice.getText();
    }
}