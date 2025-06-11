package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePage extends AbstractPage {

    private final By pageTitle = By.xpath("//h3[contains(@class, 'bloko-header-promo-3')]");
    private final By findButton = By.xpath("//span[contains(@class, 'magritte-button__label_') and text() = 'Найти']");
    private final By searchInput = By.xpath("//input[@id='a11y-search-input']");
    private final By loginButton = By.xpath("//a[@data-qa='login' and normalize-space(text())='Войти']");

    public WelcomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
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

    public boolean isLoginButton() {
        return isVisible(loginButton);
    }

    public LoginPage goToLoginPage() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver, wait).waitUntilLoaded();
    }
}
