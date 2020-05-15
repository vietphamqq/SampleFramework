package utils.factory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Config;
import utils.common.Generator;

public class PredefineCap {

    // -------------------- LOCAL ----------
    public static final DesiredCapabilities MOBILE_WEB = new DesiredCapabilities() {
        {
            setCapability("automationName", "UiAutomator2");
            setCapability("platformName", "Android");
            setCapability("platformVersion", "9");
            setCapability("deviceName", "Nexus");
            setCapability("browserName", "Chrome");
            setCapability("chromedriverExecutable", "src/test/resources/chromedriver");
        }
    };

    public static final DesiredCapabilities DESKTOP_WEB = new DesiredCapabilities() {
        {
            setBrowserName("Chrome");
            setCapability("build", Config.ENV.BS_BUILD_WEB());
        }
    };

    public static final DesiredCapabilities ANDROID = new DesiredCapabilities() {
        {
            setCapability("automationName", "UiAutomator2");
            setCapability("platformName", "Android");
            setCapability("udid", "Device UDID");
            setCapability("deviceName", Generator.getConnectedAndroidDeviceName());
            setCapability("appPackage", Config.ENV.ANDROID_PACKAGE());
            setCapability("appActivity", Config.ENV.ANDROID_ACTIVITY());
            if (Config.ENV.REQUIRE_INSTALL()) {
                setCapability("app", String.format("%s/Downloads/", System.getProperty("user.home")) + Config.ENV.ANDROID_LOCAL_APP_FILE());
            } else {
                setCapability("fullReset", false);
                setCapability("noReset", true);
            }
            setCapability("autoDismissAlerts", "true");
            setCapability("newCommandTimeout", "1000");
        }
    };

    public static final DesiredCapabilities IOS = new DesiredCapabilities() {
        {
            setCapability("automationName", "XCUITest");
            setCapability("platformName", "iOS");
            setCapability("platform", "iOS");
            setCapability("bundleId", Config.ENV.IOS_BUNDLE());
            setCapability("platformVersion", "12.4.1");
            setCapability("deviceName", Generator.getConnectedIOSDeviceName());
            setCapability("xcodeOrgId", "Your XCODE ORG ID");
            setCapability("xcodeSigningId", "iPhone Developer");
            setCapability("udid", "DEVICE UDID");
            setCapability("os", "ios");
            setCapability("newCommandTimeout", "1000");
            if (Config.ENV.REQUIRE_INSTALL()) {
                setCapability("fullReset", true);
                setCapability("app", String.format("%s/Downloads/", System.getProperty("user.home")) + Config.ENV.IOS_LOCAL_APP_FILE());
            }
            setCapability("updatedWDABundleId", "com.xxxx.WebdriverAgentRunner");
            setCapability("autoDismissAlerts", "true");
        }
    };
    // --------------------- BROWSER STACK -------------

    public static final DesiredCapabilities BS_DESKTOP_WEB = new DesiredCapabilities() {
        {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            setCapability("browser", "Chrome");
            setCapability("browser_version", "76.0");
            setCapability("os", "OS X");
            setCapability("os_version", "Mojave");
            setCapability("resolution", "1600x1200");
            setCapability("platformName", "MAC");
            setCapability("browserstack.timezone", "UTC");
            setCapability(ChromeOptions.CAPABILITY, options);
            setCapability("build", Config.ENV.BS_BUILD_WEB());
        }
    };

    public static final DesiredCapabilities BS_ANDROID = new DesiredCapabilities() {
        {
            setCapability("browserstack.appium_version", "1.13.0");
            setCapability("automationName", "UiAutomator2");
            setCapability("device", "Samsung Galaxy S10");
            setCapability("project", "IronGate");
            setCapability("build", "Android Integration Test");
            setCapability("browserstack.idleTimeout", "300");
            setCapability("os_version", "9.0");
            setCapability("app", Config.ENV.ANDROID_APP_ID());
            setCapability("autoDismissAlerts", "true");
            setCapability("browserstack.timezone", Config.ENV.BS_TIMEZONE());
        }
    };

    public static final DesiredCapabilities BS_IOS = new DesiredCapabilities() {
        {
            setCapability("browserstack.appium_version", "1.13.0");
            setCapability("automationName", "XCUITest");
            setCapability("build", "IOS Integration Test");
            setCapability("device", "iPhone XS");
            setCapability("project", "IronGate");
            setCapability("browserstack.idleTimeout", "300");
            setCapability("os_version", "12.1");
            setCapability("app", Config.ENV.IOS_APP_ID());
            setCapability("autoDismissAlerts", "true");
            setCapability("browserstack.timezone", Config.ENV.BS_TIMEZONE());
        }
    };
}
