package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class VkLoginPage extends AbstractPage {

    private final By phoneNumberInput = By.xpath("//input[@inputmode='tel']");
    private final By emailInput = By.xpath("//input[@name='login']");
    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By submitButton = By.xpath("//button[@type='submit']");

    private final By captcha = By.xpath("//form[@class='vkc__CaptchaPopup__container']");

    private final By emailSwitchButton = By.xpath("//label[.//input[@data-test-id='email-id']]");

    public VkLoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public VkLoginPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput));
        return this;
    }

    public ApplicantPage logIn(String email, String password) {

        wait.until(ExpectedConditions.elementToBeClickable(emailSwitchButton));
        driver.findElement(emailSwitchButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        driver.findElement(submitButton).click();

        try {
            WebDriverWait captchaWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            captchaWait.until(ExpectedConditions.visibilityOfElementLocated(captcha));

            System.out.println("SSO captcha detected!");
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(d -> !d.getCurrentUrl().contains("captcha"));
        } catch (TimeoutException e) {

        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);

        WebElement oldButton = driver.findElement(submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        wait.until(ExpectedConditions.stalenessOf(oldButton));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        try {
            WebDriverWait captchaWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            captchaWait.until(ExpectedConditions.visibilityOfElementLocated(captcha));

            System.out.println("SSO captcha detected!");
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(d -> !d.getCurrentUrl().contains("captcha"));
        } catch (TimeoutException e) {

        }

        return new ApplicantPage(driver, wait).waitUntilLoaded();
    }
}