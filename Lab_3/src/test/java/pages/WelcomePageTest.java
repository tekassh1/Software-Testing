package pages;

import org.example.pages.LoginPage;
import org.example.pages.WelcomePage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WelcomePageTest extends TestsBase {

    @Test
    public void welcomePageElementsTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            assertTrue(welcomePage.isWelcomePage());
            assertTrue(welcomePage.isFindButton());
            assertTrue(welcomePage.isSearchInput());
            assertTrue(welcomePage.isLoginButton());
        });
    }

    @Test
    public void goToLoginPageTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WelcomePage welcomePage = new WelcomePage(driver, wait);

            LoginPage loginPage = welcomePage.goToLoginPage();

            assertTrue(loginPage.isLoginPage());
        });
    }
}
