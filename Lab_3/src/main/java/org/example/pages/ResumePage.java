package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResumePage extends AbstractPage {

    private final By geoSwitcherSearchInput = By.xpath("//input[@data-qa='geo-switcher-search']");
    private final By headerTitle = By.xpath("//h4[@data-qa='title']");
    private final By resumeEditButton = By.xpath("//a[@data-qa='resume-edit']");
    private final By downloadResumeButton = By.xpath("//button[@data-qa='resume-download-button']");
    private final By pdfButton = By.xpath("//a[contains(@href, 'resume_converter')]//span[contains(text(), 'Adobe Reader · pdf')]");

    public ResumePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public ResumePage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle));
        return this;
    }

    public boolean isResumePage() {
        return isVisible(headerTitle);
    }

    public void goToResume() {
        WebElement resumeElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                resumeEditButton
        ));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                resumeElement
        );

        driver.findElement(resumeEditButton).click();
        new Actions(driver)
                .pause(Duration.ofMillis(2000))
                .perform();
    }

    public void scrollThroughPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i <= 1000; i += 100) {
            js.executeScript("window.scrollTo({ top: " + i + ", behavior: 'smooth' });");
            new Actions(driver)
                    .pause(Duration.ofMillis(300))
                    .perform();
        }

        for (int i = 1000; i >= 0; i -= 100) {
            js.executeScript("window.scrollTo({ top: " + i + ", behavior: 'smooth' });");
            new Actions(driver)
                    .pause(Duration.ofMillis(300))
                    .perform();
        }
    }

    public void downloadResume() {
        WebElement download = wait.until(ExpectedConditions.elementToBeClickable(downloadResumeButton));
        download.click();

        WebElement pdfDownload = wait.until(ExpectedConditions.elementToBeClickable(pdfButton));
        pdfDownload.click();
    }
}