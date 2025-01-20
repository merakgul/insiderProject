package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Methods {

    private WebDriver driver;
    private WebDriverWait wait;

    public Methods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
    }

    public void waitForElementDisplayed(By webElement) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
    }

    public void waitForElementToBeClickable(By webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void click(By webElement) {
        driver.findElement(webElement).click();
    }

    public void select(By webElement, String text) {
        Select dropdown = new Select(driver.findElement(webElement));
        dropdown.selectByVisibleText(text);
    }

    public void hoverElement(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement).perform();
    }

    public void clickKeyboard(By webElement, Keys keyboard) {
        driver.findElement(webElement).sendKeys(keyboard);
    }

    public void waitForUrlToContain(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    // Sayfa işlem metotları
    public void openCompanyPage() {
        waitForElementDisplayed(Elements.companyPage);
        click(Elements.companyPage);
    }

    public void openCareersPage() {
        waitForElementDisplayed(Elements.careersPage);
        click(Elements.careersPage);
    }

    public void clickFindJobButton() {
        click(Elements.findJobBtn);
    }

    public void openSeeAllQAJobs() {
        waitForElementToBeClickable(Elements.seeAllQAJobsBtn);
        click(Elements.seeAllQAJobsBtn);
    }

    public void selectLocation(String location) throws InterruptedException {
        waitForElementDisplayed(Elements.locationDropdown);
        click(Elements.locationDropdown);
        waitForElementToBeClickable(Elements.locationSelect);
        waitForElementToBeClickable(Elements.locationSelectOption);
        Thread.sleep(10000);
        select(Elements.locationSelect, location);
    }
}
