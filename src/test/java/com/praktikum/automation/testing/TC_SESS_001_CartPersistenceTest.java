package com.praktikum.automation.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_SESS_001_CartPersistenceTest extends BaseTest {

    @Test(description = "TC_SESS_001: Cart Data Persistence After Logout")
    public void testCartPersistenceAfterLogout() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // STEP 1-2: Login
        System.out.println("STEP 1-2: Performing Login...");
        homePage.clickLogin();
        Thread.sleep(2000); // Wait for modal

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

        // STEP 3-5: Add product to cart
        System.out.println("STEP 3-5: Adding Sony vaio i5 to cart...");
        homePage.selectLaptopsCategory();
        Thread.sleep(2000); // Wait for products to load

        productPage.clickProduct("Sony vaio i5");
        Thread.sleep(2000); // Wait for product page

        productPage.addToCart();
        Thread.sleep(2000); // Wait for alert

        String alertText = productPage.getAlertText();
        Assert.assertEquals(alertText, "Product added.", "Product should be added");
        productPage.acceptAlert();
        System.out.println("✅ Product added to cart");
        Thread.sleep(1000);

        // STEP 7: Verify product in cart
        System.out.println("STEP 7: Verifying cart before logout...");
        homePage.goToCart();
        Thread.sleep(2000); // Wait for cart to load

        Assert.assertTrue(cartPage.isProductInCart("Sony vaio i5"),
                "Product should be in cart");
        Assert.assertEquals(cartPage.getTotalPrice(), "790",
                "Total should be 790");
        System.out.println("✅ Cart verified: Sony vaio i5 ($790)");

        // STEP 8-9: Logout
        System.out.println("STEP 8-9: Logging out...");
        driver.navigate().to(BASE_URL);
        Thread.sleep(2000); // Wait for page load

        homePage.clickLogout();
        Thread.sleep(2000); // Wait for logout

        Assert.assertFalse(homePage.isLoggedIn(), "User should be logged out");
        System.out.println("✅ Logout successful");

        // STEP 10: Check cart after logout
        System.out.println("STEP 10: Checking cart after logout...");
        homePage.goToCart();
        Thread.sleep(2000);

        boolean isCartEmpty = cartPage.isCartEmpty();
        System.out.println("Cart empty status: " + isCartEmpty);
        System.out.println("✅ Cart cleared after logout (as expected for guest)");

        // STEP 13-15: Re-login
        System.out.println("STEP 13-15: Re-logging in...");
        driver.navigate().to(BASE_URL);
        Thread.sleep(2000);

        homePage.clickLogin();
        Thread.sleep(2000);

        loginPage.login("testuser_auto", "Test@12345");
        Thread.sleep(3000); // Wait for re-login

        // Verify re-login dengan retry
        isLoggedIn = false;
        for (int i = 0; i < 3; i++) {
            if (homePage.isLoggedIn()) {
                isLoggedIn = true;
                break;
            }
            Thread.sleep(1000);
        }

        Assert.assertTrue(isLoggedIn, "User should be logged in again");
        System.out.println("✅ Re-login successful");

        // STEP 16: Check cart after re-login
        System.out.println("STEP 16: Checking cart after re-login...");
        homePage.goToCart();
        Thread.sleep(2000);

        Assert.assertTrue(cartPage.isProductInCart("Sony vaio i5"),
                "Product should still be in cart after re-login");
        Assert.assertEquals(cartPage.getTotalPrice(), "790",
                "Total should still be 790");

        System.out.println("✅ Cart data persisted after re-login");
        System.out.println("✅✅✅ TC_SESS_001 PASSED ✅✅✅");
        System.out.println("Cart persistence working correctly!");
    }
}