package pages;

import org.example.pages.ApplicantPage;
import org.example.pages.LoginPage;
import org.example.pages.VkLoginPage;
import org.example.pages.WelcomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest extends TestsBase {
    @Test
    public void loginPageElementsTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            assertTrue(loginPage.isLoginPage());
            assertTrue(loginPage.isPreLoginButton());
        });
    }

    @Test
    public void loginPagePreTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            assertTrue(loginPage.isLoginPage());
            assertTrue(loginPage.isPreLoginButton());
        });
    }

    @Test
    public void loginPageLoginTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            assertTrue(applicantPage.isApplicantPage());
        });
    }

    @Test
    public void loginPageIncorrectPassTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            assertThrows(RuntimeException.class, () ->
                    loginPage.login(hhUsername, "123456")
            );
        });
    }

    @Test
    public void loginPageIncorrectEmailTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            assertThrows(RuntimeException.class, () ->
                    loginPage.login("skdhfkshdfsf", "123456")
            );
        });
    }

    @Test
    public void loginPageSsoTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            VkLoginPage vkLoginPage = loginPage.loginWithSSO();

            ApplicantPage applicantPage = vkLoginPage.logIn(vkEmail, vkPassword);

            assertTrue(applicantPage.isApplicantPage());
        });
    }

}