package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.Elements;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class InsiderSeleniumTest extends BaseTest {

    @Test
    public void shouldCareersPageBeOpened() {
        methods.openCompanyPage();
        methods.openCareersPage();
        methods.clickFindJobButton();

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("careers") : "URL is not right, does not contains 'careers' : " + currentUrl;

        WebElement findJobBtn = driver.findElement(Elements.findJobBtn);
        assert findJobBtn.isDisplayed() : "Find Job button is not visible!";
    }

    @Test
    public void shouldSeeQualityAssurancePositions() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
        methods.openSeeAllQAJobs();
        methods.selectLocation("Istanbul, Turkiye");

        methods.waitForElementToBeClickable(Elements.jobsListItem);
        //sleep method has been added to wait for right QA jobs lists for the right location
        methods.sleep(2000);
        List<WebElement> jobList = driver.findElements(Elements.jobListQAItems);
        assertTrue(jobList.size() > 0, "Right job scans could not be found");

        for (WebElement job : jobList) {
            String position = job.findElement(Elements.positions).getText().toLowerCase().trim();
            String department = job.findElement(Elements.departments).getText();
            String location = job.findElement(Elements.locations).getText();

            System.out.println("Position Text: " + position);

            assertTrue(
                    position.matches("(?i).*(quality assurance|qa).*"),
                    "Position does not contain 'Quality Assurance' or 'QA': " + position
            );
            assertTrue(department.contains("Quality Assurance"), "Department does not contain 'Quality Assurance'");
            assertTrue(location.contains("Istanbul, Turkiye"), "Location does not contain 'Istanbul, Turkiye'");
        }

        methods.hoverElement(jobList.get(0));
        WebElement viewRoleButton = jobList.get(0).findElement(Elements.viewRoleBtn);
        methods.clickviaJS(viewRoleButton);

        String mainWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        methods.waitForUrlToContain("lever.co");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("lever.co"), "Redirect did not go to the Lever Application form page. Current URL is: " + currentUrl);
    }
}
