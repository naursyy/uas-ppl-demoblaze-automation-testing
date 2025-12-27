package com.praktikum.automation.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "totalp")
    private WebElement totalPrice;

    @FindBy(css = "tr td:nth-child(2)")
    private List<WebElement> productNames;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPlaceOrder() {
        clickElement(placeOrderButton);
    }

    public String getTotalPrice() {
        waitForElement(totalPrice);
        return totalPrice.getText();
    }

    public boolean isProductInCart(String productName) {
        for (WebElement product : productNames) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public int getCartItemCount() {
        return productNames.size();
    }

    public boolean isCartEmpty() {
        return productNames.isEmpty();
    }
}