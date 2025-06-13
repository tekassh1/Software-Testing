package pages;

import org.example.pages.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicantPageTest extends TestsBase {
    @Test
    public void goToSettingsPageTest() {
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
    public void logOutTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            assertTrue(applicantPage.isApplicantPage());

            welcomePage = applicantPage.logOut();

            assertTrue(welcomePage.isWelcomePage());
        });
    }

    @Test
    public void changeLocationIncorrectTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            assertFalse(applicantPage.changeCitySearch("abcdefzzzzzzzz"));
        });
    }

    @Test
    public void changeLocationTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            applicantPage.changeLocation();

            assertTrue(applicantPage.isMoscowLoc());
        });
    }

    @Test
    public void SearchForVacancyTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            applicantPage.searchForVacancy("Охранник");

            assertTrue(applicantPage.isVacancyFound());
        });
    }

    @Test
    public void searchForNonExistingVacancyTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            applicantPage.searchForVacancy("kjfhkdjfhkgjhdkfg");

            assertFalse(applicantPage.isVacancyFound());
        });
    }

    @Test
    public void searchFiltersTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            applicantPage.searchForVacancy("Охранник");
            applicantPage.goAdvancedSearch();

            applicantPage.setFilters();

            assertTrue(applicantPage.isVacancyFound());
        });
    }

    @Test
    public void ApplyTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            String vacancyId = applicantPage.applyRandomVacancy();
            applicantPage.goToAppliesPage();

            assertTrue(applicantPage.isApplyPresent());

            applicantPage.deleteApplication(vacancyId);
        });
    }

    @Test
    public void RemoveApplyTest() {
        drivers.stream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            String vacancyId = applicantPage.applyRandomVacancy();
            applicantPage.goToAppliesPage();

            applicantPage.deleteApplication(vacancyId);
        });
    }

    @Test
    public void AddToFeaturedTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            String vacancyId = applicantPage.addRandomVacancyToFavorites();
            applicantPage.goToFavorites();

            assertTrue(applicantPage.isFavoritePresent(vacancyId));
        });
    }

    @Test
    public void RemoveFromFeaturedTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            String vacancyId = applicantPage.addRandomVacancyToFavorites();
            applicantPage.goToFavorites();

            applicantPage.removeFavorite(vacancyId);

            assertFalse(applicantPage.isFavoritePresent(vacancyId));
        });
    }

    @Test
    public void goToResumePageTest() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WelcomePage welcomePage = new WelcomePage(driver, wait);
            LoginPage loginPage = welcomePage.goToLoginPage();
            ApplicantPage applicantPage = loginPage.login(hhUsername, hhPass);

            ResumePage resumePage = applicantPage.goToResumePage();

            assertTrue(resumePage.isResumePage());
        });
    }
}
