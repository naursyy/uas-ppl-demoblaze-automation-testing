package com.praktikum.automation.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AUTH_001_InvalidLoginTest extends BaseTest {

    @Test(description = "TC_AUTH_001: Login with Invalid Credentials")
    public void testInvalidLogin() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // SCENARIO 1: Invalid Username
        System.out.println("SCENARIO 1: Testing invalid username...");
        homePage.clickLogin();
        Thread.sleep(1000);

        loginPage.login("nonexistent_user_999", "AnyPassword123");
        Thread.sleep(1000);

        String alertText = loginPage.getAlertText();
        Assert.assertEquals(alertText, "User does not exist.",
                "Should show user not exist error");
        loginPage.acceptAlert();
        Thread.sleep(1000);
        System.out.println("✅ Scenario 1 passed");

        // SCENARIO 2: Invalid Password
        System.out.println("SCENARIO 2: Testing invalid password...");
        loginPage.login("testuser_auto", "WrongPassword000");
        Thread.sleep(1000);

        alertText = loginPage.getAlertText();
        Assert.assertEquals(alertText, "Wrong password.",
                "Should show wrong password error");
        loginPage.acceptAlert();
        Thread.sleep(1000);
        System.out.println("✅ Scenario 2 passed");

        // SCENARIO 3: Empty Fields
        System.out.println("SCENARIO 3: Testing empty fields...");
        loginPage.login("", "");
        Thread.sleep(1000);

        alertText = loginPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.",
                "Should show fill fields error");
        loginPage.acceptAlert();
        System.out.println("✅ Scenario 3 passed");

        System.out.println("✅✅✅ TC_AUTH_001 PASSED ✅✅✅");
        System.out.println("All invalid login scenarios tested successfully!");
    }
}