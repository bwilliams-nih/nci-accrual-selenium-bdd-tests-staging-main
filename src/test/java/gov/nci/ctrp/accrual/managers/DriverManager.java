package gov.nci.ctrp.accrual.managers;

import gov.nci.ctrp.accrual.utils.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.HashMap;

public class DriverManager {

    private WebDriver driver;
    private static DriverType driverType;

    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
    }

    private void createDriver() {
        switch (driverType) {
            case CHROMEHEADLESS -> {
                WebDriverManager.chromedriver().clearDriverCache();
                WebDriverManager.chromedriver().clearResolutionCache();
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--remote-allow-origins=*");
 //               options.addArguments("--remote-debugging-port=9222");
                options.addArguments("--headless");
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                HashMap<String, Object> chromePref = new HashMap<>();
                chromePref.put("profile.default_content_settings.popups", 0);
                chromePref.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads");
                options.setExperimentalOption("prefs", chromePref);
                driver = new ChromeDriver(options);
            }
            case CHROME -> {
                WebDriverManager.chromedriver().clearDriverCache();
                WebDriverManager.chromedriver().clearResolutionCache();
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                HashMap<String, Object> chromePref = new HashMap<>();
                chromePref.put("profile.default_content_settings.popups", 0);
                chromePref.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads");
                options.setExperimentalOption("prefs", chromePref);
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                Dimension d = new Dimension(1920, 1080);
                driver.manage().window().setSize(d);
            }
            case FIREFOXHEADLESS -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
        }
    }

    public WebDriver getDriver() {
        if (driver == null) createDriver();
        return driver;
    }

    public void closeDriver() {
        driver.close();
    }
}