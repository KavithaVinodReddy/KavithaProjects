package orangeHRM;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import orangeHRM.utils.BrowserFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        tags = {"@end2end"},
        format = {"json:target/cucumber.json", "html:target/site/cucumber-pretty"})
public class RunTest {
    static Logger logger = Logger.getLogger(RunTest.class);

    @BeforeClass
    public static void openBrowser() {
        logger.info("start the browser before the class");
        BrowserFactory.startBrowser();
    }

    @AfterClass
    public static void closeBrowser() {
        logger.info("close the browser after the class");
        BrowserFactory.stopBrowser();
    }
}