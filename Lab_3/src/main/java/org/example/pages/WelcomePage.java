package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WelcomePage extends AbstractPage {

    private final By pageTitle = By.xpath("//h3[contains(@class, 'bloko-header-promo-3')]");
    private final By findButton = By.xpath("//span[contains(@class, 'magritte-button__label_') and text() = 'Найти']");
    private final By searchInput = By.xpath("//input[@id='a11y-search-input']");
    private final By loginButton = By.xpath("//a[@data-qa='login' and normalize-space(text())='Войти']");
    private final By enterButton = By.xpath("//button[@data-qa='submit-button']");
    private final By applicantButton = By.xpath("//div[@data-qa='account-type-card-APPLICANT']");
    private final By citySubmitButton = By.xpath("//button[@data-qa='region-clarification-submit-button']");
    private final By cookiesSubmitButton = By.xpath("//button[@data-qa='cookies-policy-informer-accept']");

    public WelcomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WelcomePage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return this;
    }

    public boolean isWelcomePage() {
        return isVisible(pageTitle);
    }

    public boolean isFindButton() {
        return isVisible(findButton);
    }

    public boolean isSearchInput() {
        return isVisible(searchInput);
    }

//    public boolean isLoginButton() {
//        return isVisible(loginButton);
//    }

    public LoginPage goToLoginPage() {

        try {
            WebElement cookiesButton = wait.until(
                    ExpectedConditions.elementToBeClickable(cookiesSubmitButton)
            );
            cookiesButton.click();

        } catch (Exception e) {}

        ((JavascriptExecutor) driver).executeScript(
                "var iframe = document.querySelector('iframe.uxfeedback-widget');" +
                        "if (iframe) iframe.remove();"
        );
        driver.findElement(loginButton).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(applicantButton));
            driver.findElement(applicantButton).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(enterButton));
            driver.findElement(enterButton).click();
        }

        return new LoginPage(driver, wait).waitUntilLoaded();
    }
}
