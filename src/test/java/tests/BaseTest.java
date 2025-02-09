package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.Methods;

public class BaseTest {

    protected WebDriver driver;
    protected Methods methods;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); // Varsayılan tarayıcı Chrome

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Geçerli bir tarayıcı parametresi seçin: chrome veya firefox");
        }

        methods = new Methods(driver);
        driver.get("https://useinsider.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}