package page_objects.desktop_web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Config;
import utils.factory.DriverFactory;

public class BasePage {
    private final static Logger LOGGER = LogManager.getLogger(BasePage.class);

    public static void goToHomePage() {
        String URL = Config.ENV.WEB_URL();
        LOGGER.info(String.format("Going to Needle by environment: %s", URL));
        DriverFactory.getWebDriver().get(URL);
    }

}
