package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Methods {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Methods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public void waitForElementDisplayed(By webElement) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
    }

    public void waitForElementToBeClickable(By webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void click(By webElement) {
        waitForElementToBeClickable(webElement);
        driver.findElement(webElement).click();
    }

    public void select(By webElement, String text) {
        waitForElementToBeClickable(webElement);
        new Select(driver.findElement(webElement)).selectByVisibleText(text);
    }

    public void hoverElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).perform();
    }

    public void waitForUrlToContain(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public void openCompanyPage() {
        click(Elements.companyPage);
    }

    public void openCareersPage() {
        click(Elements.careersPage);
    }

    public void clickFindJobButton() {
        click(Elements.findJobBtn);
    }

    public void openSeeAllQAJobs() {
        click(Elements.seeAllQAJobsBtn);
    }

    public void selectLocation(String location) {
        waitForElementDisplayed(Elements.locationDropdown);
        click(Elements.locationDropdown);
        waitForElementToBeClickable(Elements.locationSelect);
        waitForElementToBeClickable(Elements.locationSelectOption);
        waitForElementToBeClickable(Elements.filteredByQADepartment);
        select(Elements.locationSelect, location);
    }

    public void sleep(Integer sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickviaJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}