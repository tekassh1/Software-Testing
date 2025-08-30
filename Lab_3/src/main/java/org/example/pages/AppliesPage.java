package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AppliesPage extends AbstractPage {

    private final By pageLabel = By.xpath("//h1[@data-qa='title' and contains(text(), 'Отклики и\u00A0приглашения')]");

    public AppliesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public AppliesPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLabel));
        return this;
    }

    public boolean isApplyPresent() {
        List<WebElement> items = driver.findElements(By.xpath("//*[@data-qa='negotiations-item']"));
        return !items.isEmpty();
    }

    public void deleteApplication(String vacancyId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By vacancyLocator = By.xpath(
                "//div[@data-qa='negotiations-item']" +
                        "[.//a[contains(@href, 'vacancy/" + vacancyId + "')]]"
        );

        WebElement vacancyItem = wait.until(
                ExpectedConditions.presenceOfElementLocated(vacancyLocator)
        );

        By checkboxLocator = By.xpath(
                ".//input[@data-qa='negotiations-item-checkbox']"
        );

        WebElement checkbox = vacancyItem.findElement(checkboxLocator);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});" +
                        "arguments[0].click();",
                checkbox
        );

        WebElement deleteButton = wait.until(driver -> {
            WebElement btn = driver.findElement(
                    By.xpath("//button[@data-qa='negotiations-batch-remove']")
            );
            return btn.isEnabled() ? btn : null;
        });

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);

        WebElement confirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-qa='magritte_modal_buttons_delete']")
                )
        );
        confirmButton.click();
    }

    public boolean isApplicationPresent(String vacancyId) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            By vacancyLocator = By.xpath(
                    "//div[@data-qa='negotiations-item']" +
                            "[.//a[contains(@href, 'vacancy/" + vacancyId + "')]]"
            );

            return wait.until(ExpectedConditions.visibilityOfElementLocated(vacancyLocator)) != null;

        } catch (TimeoutException e) {
            return false;
        }
    }
}