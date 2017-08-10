package orangeHRM.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    protected static WebDriver driver;

    static Logger logger = Logger.getLogger(BrowserFactory.class);

    public static void startBrowser() {

        if (driver != null) return;

        String remoteBrowser;
        if (System.getProperty("RemoteBrowser") != null) {
            remoteBrowser = System.getProperty("RemoteBrowser");
        } else {
            remoteBrowser = AutomationConstants.REMOTE_BROWSER;
        }

        if (remoteBrowser.equalsIgnoreCase("true")) {
            String browserType = "Chrome"; //default
            String path = System.getProperty("user.dir") + "/src/main/resources/Browsers/";
            if (System.getProperty("BrowserType") != null) {
                browserType = System.getProperty("BrowserType");
            } else {
                browserType = AutomationConstants.BROWSER_TYPE;
            }
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (browserType.equalsIgnoreCase("firefox")) {
                capabilities = DesiredCapabilities.firefox();

            } else if (browserType.equalsIgnoreCase("chrome")) {
                capabilities = DesiredCapabilities.chrome();

            } else if (browserType.equalsIgnoreCase("IE")) {
                capabilities = DesiredCapabilities.internetExplorer();
            }
            logger.info("Browser:" + browserType);
            capabilities.setPlatform(Platform.WIN10);
            //capabilities.setVersion("48");
            URL url = null;
            try {
                url = new URL(AutomationConstants.SELENIUM_GRID_URL);
                driver = new RemoteWebDriver(url, capabilities);
                logger.info("grid started in remote grid...");
                driver.get(AutomationConstants.URL);
                logger.info("title is " + driver.getTitle());
            } catch (Exception e) {
                logger.info("error creating the remote driver");
                e.printStackTrace();
                logger.error(e.getMessage());
                return;
            }

        } else {
            logger.info("Starting tests in local browsers...");
            String browserType = "Chrome";
            if (System.getProperty("BrowserType") != null) {
                browserType = System.getProperty("BrowserType");
            } else {
                browserType = AutomationConstants.BROWSER_TYPE;
            }
            String path = System.getProperty("user.dir") + "/src/main/resources/Browsers/";
            logger.info(path);
            if (browserType.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", path + "geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("security.insecure_password.ui.enabled", false);
                firefoxOptions.addPreference("security.insecure_field_warning.contextual.enabled", false);

                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                firefoxOptions.addCapabilities(capabilities);
                driver = new FirefoxDriver(firefoxOptions);
            } else if (browserType.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                driver = new ChromeDriver(capabilities);
            } else if (browserType.equalsIgnoreCase("IE")) {
                System.setProperty("webdriver.ie.driver", path + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            } else if (browserType.equalsIgnoreCase("Safari")) {
                System.setProperty("webdriver.ie.driver", path + "SafariDriver.exe");
                driver = new SafariDriver();
            }
            logger.info("Browser:" + browserType);
        }
        driver.manage().deleteAllCookies();
        driver.get(AutomationConstants.URL);
        driver.manage().window().maximize();
    }

    public static void stopBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
            logger.info("local browser closed");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            BrowserFactory.startBrowser();
        }
        return driver;
    }
}