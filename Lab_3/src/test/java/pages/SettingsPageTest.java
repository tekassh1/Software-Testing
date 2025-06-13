package pages;

import org.example.pages.ApplicantPage;
import org.example.pages.LoginPage;
import org.example.pages.SettingsPage;
import org.example.pages.WelcomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SettingsPageTest extends TestsBase {

    @Test
    public void watchSettings() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);
            SettingsPage settingsPage = applicantPage.goToSettingsPage();

            assertTrue(settingsPage.isSettingsPage());
        });
    }

    @Test
    public void watchLinkedApps() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);
            SettingsPage settingsPage = applicantPage.goToSettingsPage();
            settingsPage.goToLinkedApps();

            assertTrue(driver.getCurrentUrl().contains("applicant/applications"));
        });
    }

    @Test
    public void changePasswordTest() {

        WebDriver driver = drivers.getFirst();

        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WelcomePage welcomePage = new WelcomePage(driver, wait);
        LoginPage loginPage = welcomePage.goToLoginPage();
        ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);
        SettingsPage settingsPage = applicantPage.goToSettingsPage();

        // Change pass
        String newPassword = "4321testPass";
        settingsPage.changePassword(hhPass, newPassword);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bloko-notification  bloko-notification_ok']")));

        welcomePage = settingsPage.logOut();
        loginPage = welcomePage.goToLoginPage();
        applicantPage = loginPage.login(hhUsername, newPassword);

        assertTrue(applicantPage.isApplicantPage());

        // Restore password
        settingsPage = applicantPage.goToSettingsPage();
        settingsPage.changePassword(newPassword, hhPass);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bloko-notification  bloko-notification_ok']")));
    }

    @Test
    public void changeEmailTest() {

        WebDriver driver = drivers.getFirst();

        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WelcomePage welcomePage = new WelcomePage(driver, wait);
        LoginPage loginPage = welcomePage.goToLoginPage();
        ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);
        SettingsPage settingsPage = applicantPage.goToSettingsPage();

        // Change email
        String newEmail = "testTestov@gmail.com";
        settingsPage.changeEmail(newEmail, hhPass);

        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='bloko-notification__content Bloko-Notification-Content']")
        ));
        assertTrue(notification.isDisplayed());

        settingsPage.changeEmail(hhUsername, hhPass);
        WebElement notification2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='bloko-notification__content Bloko-Notification-Content']")
        ));
        assertTrue(notification2.isDisplayed());
    }
}
