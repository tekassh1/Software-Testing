package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicantPage extends AbstractPage {

    private final By jobSearchInput = By.xpath("//input[@data-qa='search-input']");

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
}
