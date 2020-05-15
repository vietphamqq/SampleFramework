package utils.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DriverUtils {

    private final static Logger LOGGER = LogManager.getLogger("DriverUtil");

    public static void switchToWindow(String title) {
        LOGGER.info(String.format("Switching to window with tile %s", title));
        Set<String> WindowHandles = DriverFactory.getWebDriver().getWindowHandles();
        for (String windowId : WindowHandles) {
            DriverFactory.getWebDriver().switchTo().window(windowId);
            if (DriverFactory.getWebDriver().getTitle().equalsIgnoreCase(title)) {
                LOGGER.info(String.format("Found window with title %s", title));
                break;
            }
        }
        LOGGER.error(String.format("Can not found window with title %s", title));
    }

    public static void switchToLatestWindow() {
        List<String> BackupWindowHandles = new ArrayList<>(DriverFactory.getWebDriver().getWindowHandles());

        new WebDriverWait(DriverFactory.getWebDriver(), 10).until(x -> {
            try {
                return x.getWindowHandles().size() > 1;
            } catch (Exception e) {
                return null;
            }
        });
        List<String> WindowHandles = new ArrayList<>(DriverFactory.getWebDriver().getWindowHandles());
        DriverFactory.getWebDriver().switchTo().window(WindowHandles.get(WindowHandles.size() - 1));
        LOGGER.info(String.format("Switched to last window with tile %s", DriverFactory.getWebDriver().getTitle()));
    }

    public static void switchToFirstWindow() {
        List<String> WindowHandles = new ArrayList<>(DriverFactory.getWebDriver().getWindowHandles());
        DriverFactory.getWebDriver().switchTo().window(WindowHandles.get(0));
        LOGGER.info(String.format("Switched to first window with tile %s", DriverFactory.getWebDriver().getTitle()));
    }

    public static void switchToIframe() {
        DriverFactory.getWebDriver().switchTo().frame(DriverFactory.getWebDriver().findElement(By.xpath("//iframe")));
    }

    public static void switchToIframe(WebElement element) {
        DriverFactory.getWebDriver().switchTo().frame(element);
    }

    public static void switchToMainPage() {
        DriverFactory.getWebDriver().switchTo().defaultContent();
    }

    public static void waitForAWhile(long timeToWait, String reason) {
        try {
            LOGGER.info(String.format("Wait %d second(s) %s", timeToWait, reason));
            int numberOfBreak = (int) Math.floor(timeToWait / 60);
            int restTime = (int) (timeToWait % 60);
            for (int i = 1; i <= numberOfBreak; i++) {
                Thread.sleep(60 * 1000);
                if (DriverFactory.isWebDriverRun) {
                    DriverFactory.getWebDriver().getCurrentUrl();
                }
                if (DriverFactory.isAndroidRun) {
                    DriverFactory.getAndroidDriver().getSessionId();
                }
                if (DriverFactory.isIOSRun) {
                    DriverFactory.getIOSDriver().getSessionId();
                }
            }
            Thread.sleep(restTime * 1000);
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to wait %s(s)", timeToWait));
        }
    }

    public static void waitForAWhile(long timeToWait) {
        try {
            int numberOfBreak = (int) Math.floor(timeToWait / 60);
            int restTime = (int) (timeToWait % 60);
            for (int i = 1; i <= numberOfBreak; i++) {
                Thread.sleep(60 * 1000);
                if (DriverFactory.isWebDriverRun) {
                    DriverFactory.getWebDriver().getCurrentUrl();
                }
                if (DriverFactory.isAndroidRun) {
                    DriverFactory.getAndroidDriver().getSessionId();
                }
                if (DriverFactory.isIOSRun) {
                    DriverFactory.getIOSDriver().getSessionId();
                }

            }
            Thread.sleep(restTime * 1000);
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to wait for %ss", timeToWait));
        }
    }

    public static void switchToMainApp() {
        if (DriverFactory.isAndroidRun) {
            DriverFactory.getAndroidDriver().activateApp(Config.ENV.ANDROID_PACKAGE());
        } else if (DriverFactory.isIOSRun) {
            DriverFactory.getIOSDriver().activateApp(Config.ENV.IOS_BUNDLE());
        }
    }

    public static String getUrl() {
        return DriverFactory.getWebDriver().getCurrentUrl();
    }

    public static void goToURL(String URL) {
        DriverFactory.getWebDriver().navigate().to(URL);
    }

    public static String getTitle() {
        return DriverFactory.getWebDriver().getTitle();
    }

    public static void waitForPageLoadComplete() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 30);
        wait.until(webDriver -> ((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitForPageLoadComplete(int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(DriverFactory.getWebDriver(), timeout);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public static void waitUntilElementNoLongerExists(By locator) {
        try {
            DriverFactory.setImplicitWait(1);
            new WebDriverWait(DriverFactory.getWebDriver(), 7).until(ExpectedConditions.numberOfElementsToBe(locator, 0));
        } catch (Exception ignored) {
        } finally {
            DriverFactory.resetImplicitTimeout();
        }
    }

    public static void stopLoadingPage() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver();
        js.executeScript("window.stop();");
    }
}
