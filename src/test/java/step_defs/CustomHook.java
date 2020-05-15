package step_defs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import io.appium.java_client.appmanagement.ApplicationState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import page_objects.mobile.BaseScreen;
import utils.Config;
import utils.Platforms;
import utils.factory.DriverFactory;
import utils.factory.DriverUtils;

import java.net.MalformedURLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomHook {
    private final static Logger LOGGER = LogManager.getLogger("Cucumber");

    @Given("I open a web browser")
    @Before("@web")
    public void iOpenAWebBrowser() throws MalformedURLException {
        DriverFactory.createWebInstance();
        DriverFactory.initPages("page_objects.desktop_web", DriverFactory.getWebDriver());
    }

    @Given("^I open (iOS|Android) app$")
    public void beforeApp(String platform) throws MalformedURLException {
        LOGGER.warn("Opening Mobile App");
        if (platform.equalsIgnoreCase("ios")) {
            DriverFactory.startAppiumService();
            DriverFactory.createMobileInstance(Platforms.IOS);
            assertThat(DriverFactory.getIOSDriver().queryAppState(Config.ENV.IOS_BUNDLE()))
                    .as("The running app must be Mobile App").isEqualTo(ApplicationState.RUNNING_IN_FOREGROUND);
        } else {
            DriverFactory.startAppiumService();
            DriverFactory.createMobileInstance(Platforms.ANDROID);
            assertThat(DriverFactory.getAndroidDriver().getCurrentPackage()).isEqualTo(Config.ENV.ANDROID_PACKAGE());
        }

        DriverUtils.waitForAWhile(5, "Loading app");
    }

    @After()
    public void afterEachScenario(Scenario scenarioResult) {
        try {
            if (scenarioResult.isFailed()) {
                testDataEmbeddedOnFail(scenarioResult);
                screenshotOnFail(scenarioResult);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to take screenshot");
        } finally {
            DriverFactory.dismissInstances();
            BaseScreen.dismissScreens();
            DriverFactory.stopAppiumService();
        }
    }

    private void testDataEmbeddedOnFail(Scenario scenario) {
        String url = "";
        if (DriverFactory.isWebDriverRun) {
            url = "\nURL: " + DriverFactory.getWebDriver().getCurrentUrl();
        }
        scenario.write("TEST DATA USED:\n" + "{YOUR CUSTOM OUTPUT}" + url);
    }

    private void screenshotOnFail(Scenario scenarioResult) {
        screenshotByPlatform(scenarioResult);
        String yourCustomOutput = "";
        LOGGER.error(String.format("The Test was failed with following test data: %s", yourCustomOutput));
        LOGGER.info(String.format("Test result: %s", scenarioResult.getStatus()));
    }

    private void screenshotByPlatform(Scenario scenario) {
        if (DriverFactory.isWebDriverRun) {
            LOGGER.info("Embedding Web SS");
            byte[] webss = ((RemoteWebDriver) DriverFactory.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(webss, "image/png");
        }
        if (DriverFactory.isAndroidRun) {
            LOGGER.info("Embedding Android SS");
            byte[] mobiless = ((RemoteWebDriver) DriverFactory.getAndroidDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(mobiless, "image/png");
        }
        if (DriverFactory.isIOSRun) {
            LOGGER.info("Embedding IOS SS");
            byte[] mobiless = ((RemoteWebDriver) DriverFactory.getIOSDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(mobiless, "image/png");
        }
    }
}
