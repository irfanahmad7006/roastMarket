package applicationHooks;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;

import java.util.Properties;

public class Hooks {
    private Properties prop;
    private ConfigFileReader configReader;
    private WebDriver driver;
    private DriverFactory driverFactory;

    @Before(order = 0)
    public void getProperties() {
        configReader = new ConfigFileReader();
        prop = configReader.initiateProperties();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.setUpBrowser(browserName);
        String url = prop.getProperty("url");
        driver.get(url);
    }

    @After(order = 0)
    public void quitDriver() {
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String scName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", scName);
            System.out.println("Scenario Failed, Scenario Name: " + scName);
        }
    }

}
