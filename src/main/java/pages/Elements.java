package pages;

import org.openqa.selenium.By;

public class Elements {

    public static final By companyPage = By.xpath("//a[contains(.,'Company')]");
    public static final By careersPage = By.xpath("//div[@class='new-menu-dropdown-layout-6-mid-container']//a[text()='Careers']");
    public static final By findJobBtn = By.xpath("//div[@class='button-group d-flex flex-row']");
    public static final By seeAllQAJobsBtn = By.xpath("//*[text()='See all QA jobs']");
    public static final By locationDropdown = By.xpath("//span[@id='select2-filter-by-location-container']");
    public static final By locationSelect = By.xpath("//select[@id='filter-by-location']");
    public static final By locationSelectOption = By.xpath("//select[@id='filter-by-location']/option");
    public static final By jobsListItem = By.id("jobs-list");
    public static final By positions = By.xpath("//p[@class='position-title font-weight-bold']");
    public static final By departments = By.xpath("//span[@class='position-department text-large font-weight-600 text-primary']");
    public static final By locations = By.xpath("//div[@class='position-location text-large']");
    public static final By viewRoleBtn = By.xpath("//*[text()='View Role']");
    public static final By filteredByQADepartment = By.xpath("//span[@class='select2-selection select2-selection--single']/span[.='×Quality Assurance']");
    public static final By jobListQAItems = By.xpath("//div[@data-location='istanbul-turkiye' and @data-team='qualityassurance']");
}