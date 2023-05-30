package ui;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ui.utils.Browser;

public class UITestBase {
    WebDriver driver;

    @BeforeSuite
    public void setupDriver() {
        driver = Browser.getChromeDriver();
    }

    @AfterSuite
    public void cleanupDriver() {
        driver.quit();
    }
}
