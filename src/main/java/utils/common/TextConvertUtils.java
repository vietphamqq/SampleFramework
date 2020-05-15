package utils.common;

import com.google.common.base.CaseFormat;
import org.apache.commons.text.CaseUtils;
import utils.Config;
import utils.factory.DriverFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextConvertUtils {


    private static String roundUp(double value){
        if(value%1==0){
            return String.valueOf((int) value);
        }
        else{
            return String.valueOf(value);
        }
    }

    public static String convertToCamelCase(String text) {
        return CaseUtils.toCamelCase(text, false,  null);
    }

    public static String convertCamelToKebabCase(String camelText) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN,camelText);
    }

    public static String convertToKebabCase(String text) {
        String camelText = CaseUtils.toCamelCase(text, false,  null);
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN,camelText);
    }
}
