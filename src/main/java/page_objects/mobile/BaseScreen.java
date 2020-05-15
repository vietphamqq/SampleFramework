package page_objects.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.factory.DriverFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static utils.Config.LOGGER;

public class BaseScreen {

    public static List<Object> Screen_List = new ArrayList<>();

    static <T> T initializePage(T page) {
        if (DriverFactory.isAndroidRun) {
            PageFactory.initElements(new AppiumFieldDecorator(DriverFactory.getAndroidDriver(), Duration.ofSeconds(Config.ENV.TIME_OUTS())), page);
        }
        if (DriverFactory.isIOSRun) {
            PageFactory.initElements(new AppiumFieldDecorator(DriverFactory.getIOSDriver(), Duration.ofSeconds(Config.ENV.TIME_OUTS())), page);
        }
        Screen_List.add(page);
        return page;
    }

    public static void dismissScreens() {
        LOGGER.info("Dismissing page instances");
        Screen_List.forEach(x->{
            x = null;
        } );
        Screen_List.clear();
    }

    public void swipeToRefresh(){
        LOGGER.info("Swiping from Top to Bottom to refresh the Mobile App");
        Dimension size = DriverFactory.getCurrentAppiumDriver().manage().window().getSize();
        int x = size.getWidth() / 2;
        int starty = (int) (size.getHeight() * 0.60);
        int endy = (int) (size.getHeight() * 0.10);
        new TouchAction(DriverFactory.getCurrentAppiumDriver()).press(PointOption.point(x, endy)).waitAction(waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(x, starty)).release().perform();
    }

    public void swipeFromTopToBottom(){
        Dimension size = DriverFactory.getCurrentAppiumDriver().manage().window().getSize();
        int x = size.getWidth() / 2;
        int starty = (int) (size.getHeight() * 0.60);
        int endy = (int) (size.getHeight() * 0.10);
        new TouchAction(DriverFactory.getCurrentAppiumDriver()).press(PointOption.point(x, starty)).waitAction(waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endy)).release().perform();
    }

    public void swipeFromBottomToTop(){
        Dimension size = DriverFactory.getCurrentAppiumDriver().manage().window().getSize();
        int x = size.getWidth() / 2;
        int starty = (int) (size.getHeight() * 0.60);
        int endy = (int) (size.getHeight() * 0.10);
        new TouchAction(DriverFactory.getCurrentAppiumDriver()).press(PointOption.point(x, endy)).waitAction(waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, starty)).release().perform();
    }

}
