package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    // pre-login page
    private final By mainLabel = By.xpath("//h2[@data-qa='title' and normalize-space(text())='Вход']");
    private final By preLoginButton = By.xpath("//span[contains(@class, 'magritte-button__label_') and text() = 'Войти']");

    // login page
    private final By emailSwitch = By.xpath("//input[@data-qa='credential-type-EMAIL checked']");
    private final By phoneNumberSwitch = By.xpath("//input[@data-qa='credential-type-PHONE checked']");

    private final By emailInput = By.xpath("//input[@inputmode='email']");
    private final By loginWithPassButton = By.xpath("//button[@data-qa='expand-login-by-password']");

    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@data-qa='submit-button']");

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));

        driver.findElement(emailInput).sendKeys(email);

        driver.findElement(loginWithPassButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));

        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(loginButton).click();
        return new ApplicantPage(driver, wait).waitUntilLoaded();
    }
}
