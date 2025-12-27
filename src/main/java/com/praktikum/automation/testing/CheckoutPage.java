package com.praktikum.automation.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(css = ".sweet-alert h2")
    private WebElement successMessage;

    @FindBy(css = ".sweet-alert .lead")
    private WebElement orderDetails;

    @FindBy(css = ".confirm")
    private WebElement okButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillOrderForm(String name, String country, String city,
                              String card, String month, String year) {
        inputText(nameField, name);
        inputText(countryField, country);
        inputText(cityField, city);
        inputText(cardField, card);
        inputText(monthField, month);
        inputText(yearField, year);
    }

    public void clickPurchase() {
        clickElement(purchaseButton);
    }

    public String getSuccessMessage() {
        waitForElement(successMessage);
        return successMessage.getText();
    }

    public String getOrderDetails() {
        waitForElement(orderDetails);
        return orderDetails.getText();
    }

    public void clickOk() {
        clickElement(okButton);
    }
}