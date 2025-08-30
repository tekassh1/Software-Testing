package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ApplicantPage extends AbstractPage {

    private final By jobSearchInput = By.xpath("//input[@data-qa='search-input']");
    private final By profileSettingButton = By.xpath("//a[@data-qa='mainmenu_applicantSettings']");
    private final By logoutButton = By.xpath("//button[@data-qa='mainmenu_logoffUser']");

    private final By geoSwitcher = By.xpath("//div[@data-qa='geo-switcher']");
    private final By moscowSwitcher = By.xpath("//label[.//span[text()='Москва']]");
    private final By moscowMark = By.xpath("//span[contains(@class, 'geo-switcher-text') and text()='Москва']");

    private final By geoSwitcherSearchInput = By.xpath("//input[@data-qa='geo-switcher-search']");
    private final By notFoundCity = By.xpath("//div[contains(@class, 'not-found-')]");

    private final By searchInput = By.xpath("//input[@data-qa='search-input']");

    private final By advancedSearchButton = By.xpath("//a[@data-qa='advanced-search']");
    private final By educationInput = By.xpath("//input[@data-qa='advanced-search__education-item_not_required_or_not_specified']");
    private final By experience13Switcher = By.xpath("//input[@data-qa='advanced-search__experience-item_between1And3']");
    private final By compensationInput = By.xpath("//input[@data-qa='advanced-search-salary']");
    private final By workFormatSwitcher = By.xpath("//input[@data-qa='advanced-search__work_format-item_ON_SITE']");
    private final By filtersApplyButton = By.xpath("//button[@data-qa='advanced-search-submit-button']");

    private final By appliesPageButton = By.xpath("//div[@data-qa='mainmenu_vacancyResponses']");
    private final By favouritesButton = By.xpath("//a[contains(@class, 'container') and contains(@href, 'favorite_vacancies')]");

    private final By resumePageButton = By.xpath("//div[@data-qa='mainmenu_profileAndResumes']");

    private final By optionsButtoon = By.xpath("//button[@data-qa='applicantProfileDesktopDrop-button']");

    public static final String[] JOBS = {
            "Менеджер", "Программист", "Дизайнер", "Аналитик", "Маркетолог",
            "Бухгалтер", "Юрист", "Тестировщик", "Копирайтер", "Администратор",
            "Логист", "HR-специалист", "Продавец", "Бармен", "Водитель",
            "Повар", "Учитель", "Врач", "Инженер", "Архитектор",
            "Консультант", "Секретарь", "Электрик", "Сантехник", "Фотограф",
            "Видеооператор", "Переводчик", "Строитель", "Официант", "Грузчик",
            "Системный администратор", "DevOps-инженер", "Data Scientist", "SEO-специалист", "SMM-менеджер",
            "Бренд-менеджер", "Гейм-дизайнер", "Веб-разработчик", "Android-разработчик", "iOS-разработчик",
            "Сетевой инженер", "Биотехнолог", "Геолог", "Психолог", "Журналист",
            "Редактор", "Библиотекарь", "Агроном", "Ландшафтный дизайнер", "Спасатель"
    };

    public ApplicantPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public ApplicantPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobSearchInput));
        return this;
    }

    public boolean isApplicantPage() {
        return isVisible(jobSearchInput);
    }

    public SettingsPage goToSettingsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsButtoon));
        driver.findElement(optionsButtoon).click();

        wait.until(ExpectedConditions.elementToBeClickable(profileSettingButton));
        driver.findElement(profileSettingButton).click();

        return new SettingsPage(driver, wait).waitUntilLoaded();
    }

    public WelcomePage logOut() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsButtoon));
        driver.findElement(optionsButtoon).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        driver.findElement(logoutButton).click();

        return new WelcomePage(driver, wait).waitUntilLoaded();
    }

    public void changeLocation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(geoSwitcher));
        driver.findElement(geoSwitcher).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(moscowSwitcher));
        driver.findElement(moscowSwitcher).click();
    }

    public boolean isMoscowLoc() {
        return isVisible(moscowMark);
    }

    public boolean changeCitySearch(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(geoSwitcher));
        driver.findElement(geoSwitcher).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(geoSwitcherSearchInput));
        driver.findElement(geoSwitcherSearchInput).sendKeys(city);

        List<WebElement> elements = driver.findElements(notFoundCity);
        return !elements.isEmpty();
    }

    public void searchForVacancy(String vacancy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        WebElement input = driver.findElement(searchInput);
        input.sendKeys(vacancy);
        input.sendKeys(Keys.ENTER);
        new Actions(driver)
                .pause(Duration.ofMillis(1000))
                .perform();
    }

    public boolean isVacancyFound() {
        List<WebElement> vacancies = driver.findElements(
                By.xpath("//div[@data-qa='vacancy-serp__vacancy']")
        );

        return !vacancies.isEmpty();
    }

    public void goAdvancedSearch() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(advancedSearchButton));
        driver.findElement(advancedSearchButton).click();
        new Actions(driver)
                .pause(Duration.ofMillis(1000))
                .perform();
    }

    public void setFilters() {
        wait.until(ExpectedConditions.presenceOfElementLocated(educationInput));
        wait.until(ExpectedConditions.presenceOfElementLocated(experience13Switcher));
        wait.until(ExpectedConditions.presenceOfElementLocated(compensationInput));
        wait.until(ExpectedConditions.presenceOfElementLocated(workFormatSwitcher));
        wait.until(ExpectedConditions.presenceOfElementLocated(filtersApplyButton));

        WebElement workFormatCheckbox = driver.findElement(workFormatSwitcher);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", workFormatCheckbox);
        driver.findElement(workFormatSwitcher).click();

        WebElement eduInputCheckbox = driver.findElement(educationInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", eduInputCheckbox);
        driver.findElement(educationInput).click();

        WebElement experienceInputCheckbox = driver.findElement(experience13Switcher);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", experienceInputCheckbox);
        driver.findElement(experience13Switcher).click();

        WebElement compensationInputZone = driver.findElement(compensationInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", compensationInputZone);
        driver.findElement(compensationInput).sendKeys("20000");

        driver.findElement(filtersApplyButton).click();
        new Actions(driver)
                .pause(Duration.ofMillis(1000))
                .perform();
    }

    public String applyRandomVacancy() {
        String vacancy = JOBS[(int) (Math.random() * JOBS.length)];
        searchForVacancy(vacancy);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        List<WebElement> vacancyItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@data-qa='vacancy-serp__vacancy']")
        ));

        int randomIndex = new Random().nextInt(vacancyItems.size());
        String vacancyId = null;
        WebElement respondButton = null;

        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                vacancyItems = driver.findElements(By.xpath("//div[@data-qa='vacancy-serp__vacancy']"));
                WebElement randomVacancy = vacancyItems.get(randomIndex);

                vacancyId = randomVacancy.getAttribute("data-vacancy-id");
                if (vacancyId == null) {
                    WebElement link = randomVacancy.findElement(By.xpath(".//a[@data-qa='serp-item__title']"));
                    String href = link.getAttribute("href");
                    vacancyId = href.substring(href.indexOf("vacancy/") + 8, href.indexOf("?"));
                }

                respondButton = randomVacancy.findElement(
                        By.xpath(".//a[@data-qa='vacancy-serp__vacancy_response']")
                );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                        respondButton
                );

                wait.until(ExpectedConditions.visibilityOf(respondButton));
                wait.until(ExpectedConditions.elementToBeClickable(respondButton)).click();

//                try {
                    WebElement successNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@data-qa='vacancy-response-success-standard-notification']")
                    ));

                    WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@data-qa='vacancy-response-success-standard-notification']//button[@data-qa='snackbar-close-action']")
                    ));

                    closeButton.click();

                    wait.until(ExpectedConditions.invisibilityOf(successNotification));
//                }
//                catch (Exception e) {}

                break;

            } catch (StaleElementReferenceException e) {
                if (attempt == 2) throw e; // Если последняя попытка, пробрасываем исключение
                try { Thread.sleep(2000); } catch (InterruptedException ie) {} // Ждем перед повторной попыткой
            }
        }

        return vacancyId;
    }

    public AppliesPage goToAppliesPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");
        wait.until(ExpectedConditions.elementToBeClickable(appliesPageButton)).click();
        return new AppliesPage(driver, wait).waitUntilLoaded();
    }

    public FavouritesPage goToFavoritesPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'bloko-notification__plate')]")
            ));
        } catch (TimeoutException e) {
            try {
                WebElement closeButton = driver.findElement(
                        By.xpath("//div[contains(@class, 'bloko-notification__plate')]//button[contains(@class, 'close')]")
                );
                closeButton.click();
            } catch (Exception exp) {
            }
        }

        WebElement favoritesButton = wait.until(ExpectedConditions.elementToBeClickable(favouritesButton));
        favoritesButton.click();

        return new FavouritesPage(driver, wait).waitUntilLoaded();
    }

    public String addRandomVacancyToFavorites() {
        String vacancy = JOBS[(int) (Math.random() * JOBS.length)];
        searchForVacancy(vacancy);

        List<WebElement> vacancyItems = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@data-qa='vacancy-serp__vacancy']")
                )
        );

        WebElement randomVacancy = vacancyItems.get(new Random().nextInt(vacancyItems.size()));

        WebElement link = randomVacancy.findElement(By.xpath(".//a[@data-qa='vacancy-serp__vacancy_response']"));
        String href = link.getAttribute("href");
        String vacancyId = href.substring(href.indexOf("vacancyId=") + 10, href.indexOf("&"));

        WebElement favoriteButton = randomVacancy.findElement(
                By.xpath(".//button[@data-qa='vacancy-search-mark-favorite_false']")
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                randomVacancy
        );

        wait.until(ExpectedConditions.elementToBeClickable(favoriteButton));
        favoriteButton.click();

        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-qa='notification-close-button']")
            ));
            closeButton.click();
        } catch (Exception e) {}

        return vacancyId;
    }

    public ResumePage goToResumePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resumePageButton));
        driver.findElement(resumePageButton).click();

        return new ResumePage(driver, wait).waitUntilLoaded();
    }
}