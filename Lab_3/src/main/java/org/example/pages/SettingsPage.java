package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsPage extends AbstractPage {

    private final By header = By.xpath("//h1[@class='bloko-header-1' and normalize-space(text())='Настройки']");

    private final By changePasswordButton = By.xpath("//a[@href='#password']");
    private final By oldPasswordInput = By.xpath("//input[@data-qa='settings__password-password']");
    private final By newPasswordInput = By.xpath("//input[@data-qa='settings__password-newpassword']");
    private final By newPasswordConfirmInput = By.xpath("//input[@data-qa='settings__password-newpasswordconfirm']");
    private final By passwordSaveButton = By.xpath("//input[@data-qa='settings__password-submit']");

    private final By logoutButton = By.xpath("//button[@data-qa='mainmenu_logoffUser']");

    private final By changeEmailButton = By.xpath("//a[@href='#email']");
    private final By newEmailInput = By.xpath("//input[@data-qa='settings__email-email']");
    private final By currentPasswordInput = By.xpath("//input[@data-qa='settings__email-password']");
    private final By emailSaveButton = By.xpath("//input[@data-qa='settings__email-submit']");

    private final By linkedAppsButton = By.xpath("//a[@data-qa='settings__my-applications']");

    private final By optionsButtoon = By.xpath("//button[@data-qa='applicantProfileDesktopDrop-button']");

    public SettingsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SettingsPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        return this;
    }

    public boolean isSettingsPage() {
        return isVisible(header);
    }

    public void goToLinkedApps() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkedAppsButton));
        driver.findElement(linkedAppsButton).click();
    }

    public void changePassword(String oldPassword, String newPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changePasswordButton));
        driver.findElement(changePasswordButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(oldPasswordInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordConfirmInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordSaveButton));

        driver.findElement(oldPasswordInput).clear();
        driver.findElement(oldPasswordInput).sendKeys(oldPassword);
        driver.findElement(newPasswordInput).clear();
        driver.findElement(newPasswordInput).sendKeys(newPassword);
        driver.findElement(newPasswordConfirmInput).clear();
        driver.findElement(newPasswordConfirmInput).sendKeys(newPassword);


        new Actions(driver)
                .pause(Duration.ofMillis(1000))
                .click(driver.findElement(passwordSaveButton))
                .perform();
    }

    public void changeEmail(String newEmail, String password) {
        driver.navigate().refresh();

        wait.until(ExpectedConditions.visibilityOfElementLocated(changeEmailButton));
        driver.findElement(changeEmailButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(newEmailInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPasswordInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailSaveButton));

        driver.findElement(newEmailInput).clear();
        driver.findElement(newEmailInput).sendKeys(newEmail);
        driver.findElement(currentPasswordInput).clear();
        driver.findElement(currentPasswordInput).sendKeys(password);

        new Actions(driver)
                .pause(Duration.ofMillis(15000))
                .click(driver.findElement(emailSaveButton))
                .perform();
    }

    public WelcomePage logOut() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsButtoon));
        driver.findElement(optionsButtoon).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        driver.findElement(logoutButton).click();

        return new WelcomePage(driver, wait).waitUntilLoaded();
    }
}
