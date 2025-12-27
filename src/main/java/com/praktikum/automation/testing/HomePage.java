package com.praktikum.automation.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "logout2")
    private WebElement logoutButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeText;

    @FindBy(linkText = "Phones")
    private WebElement phonesCategory;

    @FindBy(linkText = "Laptops")
    private WebElement laptopsCategory;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    public void clickLogout() {
        clickElement(logoutButton);
    }

    public String getWelcomeText() {
        waitForElement(welcomeText);
        return welcomeText.getText();
    }

    public void selectPhonesCategory() {
        clickElement(phonesCategory);
    }

    public void selectLaptopsCategory() {
        clickElement(laptopsCategory);
    }

    public void goToCart() {
        clickElement(cartLink);
    }

    public boolean isLoggedIn() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}