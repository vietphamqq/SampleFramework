package page_objects.desktop_web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GooglePage {

    @FindBy(name = "q")
    private static WebElement SearchTextBox;

    @FindBy(name = "btnK")
    private static WebElement SearchButton;

    @FindBy(id = "result-stats")
    private static WebElement ResultNumberLabel;

    public static void searchForKeyword(String keyword) {
        SearchTextBox.sendKeys(keyword);
        SearchTextBox.sendKeys(Keys.ENTER);
    }


    public static int getNumberOfResult() {
        String result = ResultNumberLabel.getText();
        String regex = " ([\\d,.]+) ";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1).replace(",", "").replace(".",""));
        }
        else {
            return 0;
        }
    }
}
