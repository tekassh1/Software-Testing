package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class LoginPage extends AbstractPage {

    // pre-login page
    private final By mainLabel = By.xpath("//h2[@data-qa='title' and normalize-space(text())='Вход']");
    private final By preLoginButton = By.xpath("//span[contains(@class, 'magritte-button__label_') and text() = 'Войти']");

    // login page
    private final By emailSwitch = By.xpath("//div[contains(@class, 'magritte-text_') and text() = 'Почта']");
    private final By phoneNumberSwitch = By.xpath("//div[contains(@class, 'magritte-text_') and text() = 'Телефон']");

    private final By emailInput = By.xpath("//input[@inputmode='email']");
    private final By loginWithPassButton = By.xpath("//button[@data-qa='expand-login-by-password']");

    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@data-qa='submit-button']");

    private final By captcha = By.xpath("//input[@data-qa='account-captcha-input']");

    // SSO
    private final By loginWithSsoButton = By.xpath("//button[@data-qa='account-login-social-show-more']");
    private final By loginWithVKButton = By.xpath("//a[@data-qa='applicant-login-social-vk']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainLabel));
        return this;
    }

    public boolean isLoginPage() {
        return isVisible(mainLabel);
    }

    public boolean isPreLoginButton() {
        return isVisible(preLoginButton);
    }

    public ApplicantPage login(String email, String password) {
        driver.findElement(preLoginButton).click();

        try {
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            String currentEmail = emailField.getAttribute("value");

            if (currentEmail == null || currentEmail.isEmpty() || !currentEmail.equals(email)) {
                emailField.clear();
                emailField.sendKeys(email);
            }
        } catch (TimeoutException e) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailSwitch)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        }

        driver.findElement(loginWithPassButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(loginButton).click();

        try {
            WebDriverWait captchaWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            captchaWait.until(ExpectedConditions.visibilityOfElementLocated(captcha));

            System.out.println("Login captcha detected!");

            new Actions(driver)
                    .pause(Duration.ofMillis(40000))
                    .perform();

        } catch (TimeoutException e) {

        }

        try {
            return new ApplicantPage(driver, wait).waitUntilLoaded();
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    public VkLoginPage loginWithSSO() {
        driver.findElement(preLoginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailSwitch));

        driver.findElement(loginWithSsoButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginWithVKButton));

        driver.findElement(loginWithVKButton).click();

        return new VkLoginPage(driver, wait).waitUntilLoaded();
    }
}
