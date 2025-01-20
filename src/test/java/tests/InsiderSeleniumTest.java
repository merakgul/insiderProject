package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.Elements;

import java.util.List;

public class InsiderSeleniumTest extends BaseTest {

    @Test
    public void shouldCareersPageBeOpened() {
        methods.openCompanyPage();
        methods.openCareersPage();
        methods.clickFindJobButton();

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("careers") : "URL doğru değil, 'careers' içermiyor: " + currentUrl;

        WebElement findJobBtn = driver.findElement(Elements.findJobBtn);
        assert findJobBtn.isDisplayed() : "Find Job butonu görünür değil!";
    }

    @Test
    public void shouldSeeQualityAssurancePositions() throws InterruptedException {
        driver.get("https://useinsider.com/careers/quality-assurance/");
        methods.openSeeAllQAJobs();
        methods.selectLocation("Istanbul, Turkey");

        List<WebElement> jobList = driver.findElements(Elements.jobsList);
        assert jobList.size() > 0 : "İş ilanları bulunamadı!";

        for (WebElement job : jobList) {
            String position = job.findElement(Elements.positions).getText();
            String department = job.findElement(Elements.departments).getText();
            String location = job.findElement(Elements.locations).getText();

            assert position.contains("Quality Assurance") : "Position does not contain 'Quality Assurance'";
            assert department.contains("Quality Assurance") : "Department does not contain 'Quality Assurance'";
            assert location.contains("Istanbul, Turkey") : "Location does not contain 'Istanbul, Turkey'";
        }

        WebElement viewRoleButton = jobList.get(0).findElement(Elements.viewRoleBtn);
        methods.waitForElementToBeClickable(Elements.viewRoleBtn);
        viewRoleButton.click();

        methods.waitForUrlToContain("lever.co");
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("lever.co") : "Redirect did not go to the Lever Application form page";
    }
}
