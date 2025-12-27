package com.praktikum.automation.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_CONT_001_ContactValidationTest extends BaseTest {

    @Test(description = "TC_CONT_001: Send Contact Message Without Name")
    public void testContactWithoutName() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        System.out.println("STEP 1-2: Opening Contact modal...");

        // Click Contact link
        WebElement contactLink = driver.findElement(By.linkText("Contact"));
        contactLink.click();
        Thread.sleep(2000); // Wait for modal animation

        // Wait for modal to appear
        WebElement contactModal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal"))
        );
        System.out.println("✅ Contact modal opened");

        // STEP 3: Fill email (valid)
        System.out.println("STEP 3: Filling email...");
        WebElement emailField = driver.findElement(By.id("recipient-email"));
        emailField.clear();
        emailField.sendKeys("test@example.com");
        Thread.sleep(500);
        System.out.println("✅ Email filled");

        // STEP 4: Leave name empty (intentionally)
        System.out.println("STEP 4: Leaving name field empty...");
        WebElement nameField = driver.findElement(By.id("recipient-name"));
        nameField.clear();
        Thread.sleep(500);
        System.out.println("✅ Name field left empty");

        // STEP 5: Fill message
        System.out.println("STEP 5: Filling message...");
        WebElement messageField = driver.findElement(By.id("message-text"));
        messageField.clear();
        messageField.sendKeys("This is a test message");
        Thread.sleep(500);
        System.out.println("✅ Message filled");

        // STEP 6: Click Send message button
        System.out.println("STEP 6: Clicking Send message...");
        WebElement sendButton = driver.findElement(
                By.xpath("//button[text()='Send message']")
        );
        sendButton.click();
        Thread.sleep(2000); // Wait for processing

        // STEP 7: Check if alert appears
        System.out.println("STEP 7: Checking for alert...");
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            System.out.println("Alert text: " + alertText);

            // BUG: System allows sending without name
            if (alertText.contains("Thanks for the message")) {
                System.out.println("❌ BUG CONFIRMED: Message sent without name!");
                System.out.println("❌ Expected: Validation error");
                System.out.println("❌ Actual: Success message appeared");

                Assert.fail("BUG: System allows contact message without name. " +
                        "Expected validation error but got: " + alertText);
            }
        } catch (Exception e) {
            System.out.println("✅ No alert appeared - validation might be working");
        }

        System.out.println("❌❌❌ TC_CONT_001 FAILED (Expected Behavior) ❌❌❌");
        System.out.println("BUG ID: BUG_DEMOBLAZE_001 - Contact validation missing");
    }
}