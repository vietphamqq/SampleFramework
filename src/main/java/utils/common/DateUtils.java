package utils.common;

import utils.Config;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {

    public static LocalDateTime getDateTimeFromString(String dateString, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateString,formatter);
    }

    public static String getCurrentDateTime(String pattern){
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of(Config.ENV.TIMEZONE()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return currentDateTime.format(formatter);
    }

    public static String updateCurrentDate(String pattern, int dateType, int amount){
        //dateType: e.g. Calendar.MINUTE, Calendar.DATE...
        java.text.DateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Config.ENV.TIMEZONE()));
        calendar.add(dateType, amount);
        return dateFormat.format(calendar.getTime());
    }
}
