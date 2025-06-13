package pages;

import org.example.pages.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResumePageTest extends TestsBase {
    @Test
    public void scrollResumeTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            ResumePage resumePage = applicantPage.goToResumePage();
            resumePage.goToResume();

            resumePage.scrollThroughPage();
        });
    }

    @Test
    public void downloadResumeTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            ResumePage resumePage = applicantPage.goToResumePage();
            resumePage.goToResume();

            resumePage.downloadResume();
        });
    }
}
