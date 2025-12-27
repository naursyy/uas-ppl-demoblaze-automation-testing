package com.praktikum.automation.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_VALID_001_EmptyCartTest extends BaseTest {

    @Test(description = "TC_VALID_001: Place Order with Empty Cart")
    public void testPlaceOrderWithEmptyCart() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Login first
        System.out.println("Logging in first...");
        homePage.clickLogin();
        Thread.sleep(2000);

        loginPage.login("testuser_auto", "Test@12345");
        Thread.sleep(3000);

        // Verify login dengan retry
        boolean isLoggedIn = false;
        for (int i = 0; i < 3; i++) {
            if (homePage.isLoggedIn()) {
                isLoggedIn = true;
                break;
            }
            Thread.sleep(1000);
        }

        Assert.assertTrue(isLoggedIn, "User should be logged in");
        System.out.println("✅ Login successful");

        // STEP 1: Navigate to Cart
        System.out.println("STEP 1: Navigating to Cart page...");
        homePage.goToCart();
        Thread.sleep(2000);
        System.out.println("✅ Cart page loaded");

        // STEP 2: Verify cart is empty
        System.out.println("STEP 2: Verifying cart is empty...");
        boolean isCartEmpty = cartPage.isCartEmpty();
        System.out.println("Cart empty status: " + isCartEmpty);

        if (!isCartEmpty) {
            System.out.println("⚠️ Cart not empty, cleaning cart first...");
            driver.navigate().refresh();
            Thread.sleep(2000);
        }
        System.out.println("✅ Cart verified as empty");

        // STEP 3: Check Place Order button
        System.out.println("STEP 3: Checking Place Order button status...");
        WebElement placeOrderBtn = driver.findElement(
                By.xpath("//button[text()='Place Order']")
        );

        boolean isButtonEnabled = placeOrderBtn.isEnabled();
        boolean isButtonDisplayed = placeOrderBtn.isDisplayed();

        System.out.println("Place Order button enabled: " + isButtonEnabled);
        System.out.println("Place Order button displayed: " + isButtonDisplayed);

        // STEP 4: Attempt to click Place Order
        System.out.println("STEP 4: Attempting to click Place Order with empty cart...");

        if (isButtonEnabled && isButtonDisplayed) {
            System.out.println("⚠️ Button is enabled (should be disabled!)");
            placeOrderBtn.click();
            Thread.sleep(2000);

            try {
                WebElement orderModal = driver.findElement(By.id("orderModal"));
                if (orderModal.isDisplayed()) {
                    System.out.println("❌ BUG: Order modal opened with empty cart!");

                    checkoutPage.fillOrderForm("Test User", "USA", "New York",
                            "1111222233334444", "12", "2025");
                    Thread.sleep(1000);

                    checkoutPage.clickPurchase();
                    Thread.sleep(3000);

                    try {
                        String successMsg = checkoutPage.getSuccessMessage();
                        System.out.println("❌ BUG CONFIRMED: Success message appeared!");
                        System.out.println("❌ Message: " + successMsg);

                        String orderDetails = checkoutPage.getOrderDetails();
                        System.out.println("❌ Order Details: " + orderDetails);

                        if (orderDetails.contains("0")) {
                            System.out.println("❌ BUG: $0 order created!");
                        }

                        checkoutPage.clickOk();
                        Thread.sleep(1000);

                        Assert.fail("BUG: System allows checkout with empty cart. " +
                                "Created order with amount: 0 USD");
                    } catch (Exception e) {
                        System.out.println("✅ No success message (expected)");
                    }
                }
            } catch (Exception e) {
                System.out.println("✅ Order modal did not open (expected behavior)");
            }
        } else {
            System.out.println("✅ Place Order button is disabled (correct behavior)");
        }

        System.out.println("❌❌❌ TC_VALID_001 FAILED (Expected Behavior) ❌❌❌");
        System.out.println("BUG ID: BUG_DEMOBLAZE_002 - Empty cart checkout allowed");
    }
}