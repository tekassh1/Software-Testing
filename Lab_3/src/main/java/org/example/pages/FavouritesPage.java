package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FavouritesPage extends AbstractPage {

    private final By pageLabel = By.xpath("//h1[@data-qa='bloko-header-1' and text()='Избранные вакансии']");

    public FavouritesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public FavouritesPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLabel));
        return this;
    }

    public void removeFavorite(String vacancyId) {
        try {
            String cardXpath = "//div[@data-qa='vacancy-serp__vacancy' and " +
                    ".//a[contains(@href, 'vacancyId=" + vacancyId + "')]]";

            WebElement vacancyCard = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(cardXpath))
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    vacancyCard
            );

            WebElement removeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            vacancyCard.findElement(
                                    By.xpath(".//button[@aria-label='Удалить из избранного']")
                            )
                    )
            );

            try {
                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
            } catch (ElementClickInterceptedException e) {
//                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeButton);
            }

        } catch (TimeoutException e) {}
    }

    public boolean isFavoritePresent(String vacancyId) {
        driver.navigate().refresh();
        List<WebElement> responseButtons = driver.findElements(By.xpath(
                "//a[contains(@href, 'vacancyId=" + vacancyId + "')]"
        ));
        return !responseButtons.isEmpty();
    }
}
