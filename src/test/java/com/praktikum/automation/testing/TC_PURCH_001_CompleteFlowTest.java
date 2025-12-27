package com.praktikum.automation.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_PURCH_001_CompleteFlowTest extends BaseTest {

    @Test(description = "TC_PURCH_001: Complete Purchase Flow - End to End")
    public void testCompletePurchaseFlow() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Initialize Page Objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // STEP 1-5: Login Process
        System.out.println("STEP 1-5: Performing Login...");
        homePage.clickLogin();
        Thread.sleep(2000); // Wait for modal to appear

        loginPage.login("testuser_auto", "Test@12345");
        Thread.sleep(3000); // Wait for login to complete

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

        // STEP 6-9: Add Product to Cart
        System.out.println("STEP 6-9: Adding product to cart...");
        homePage.selectPhonesCategory();
        Thread.sleep(2000); // Wait for products to load

        productPage.clickProduct("Samsung galaxy s6");
        Thread.sleep(2000); // Wait for product page to load

        productPage.addToCart();
        Thread.sleep(2000); // Wait for alert

        String alertText = productPage.getAlertText();
        Assert.assertEquals(alertText, "Product added.",
                "Alert should show product added");
        productPage.acceptAlert();
        System.out.println("✅ Product added to cart");
        Thread.sleep(1000);

        // STEP 10: Navigate to Cart
        System.out.println("STEP 10: Going to cart...");
        homePage.goToCart();
        Thread.sleep(2000); // Wait for cart page to load

        Assert.assertTrue(cartPage.isProductInCart("Samsung galaxy s6"),
                "Product should be in cart");
        Assert.assertEquals(cartPage.getTotalPrice(), "360",
                "Total should be 360");
        System.out.println("✅ Cart verified");

        // STEP 11-18: Checkout Process
        System.out.println("STEP 11-18: Processing checkout...");
        cartPage.clickPlaceOrder();
        Thread.sleep(2000); // Wait for modal to open

        checkoutPage.fillOrderForm("Jenny", "United States", "New York",
                "1234567890123456", "12", "2025");
        Thread.sleep(1000);

        checkoutPage.clickPurchase();
        Thread.sleep(3000); // Wait for order processing

        // STEP 19-20: Verify Order Success
        System.out.println("STEP 19-20: Verifying order...");
        Assert.assertEquals(checkoutPage.getSuccessMessage(),
                "Thank you for your purchase!",
                "Success message should appear");

        String orderDetails = checkoutPage.getOrderDetails();
        Assert.assertTrue(orderDetails.contains("360"),
                "Order amount should be 360");

        checkoutPage.clickOk();
        Thread.sleep(1000);

        System.out.println("✅✅✅ TC_PURCH_001 PASSED ✅✅✅");
        System.out.println("Complete purchase flow executed successfully!");
    }
}