package utils.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Config.LOGGER;

public class Generator {

    public static String genCurrentDate(DateFormat format) {
        Date dateTime = new Date();
        String pattern;
        switch (format) {
            case FULL:
                pattern = "yyyy/MM/dd HH:mm:ss";
                break;
            default:
            case SHORT:
                pattern = "MM-dd";
                break;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(dateTime);
    }

    public static String getConnectedAndroidDeviceName() {
        String adb_home = System.getenv("ANDROID_HOME")+"/platform-tools/";
        String device = execCmd(adb_home+"adb devices | grep \"\\bdevice\\b\"");
        return device.replace("device","").trim();
    }

    public static String getConnectedIOSDeviceName() {
        String device = execCmd("instruments -s devices | grep -v Simulator | tail -1");
        final String regex = "(.*)\\(.*";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(device);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "Iphone";
    }

    public static String getConnectedIOSDeviceUDID() {
        String device = execCmd("instruments -s devices | grep -v Simulator | tail -1");
        final String regex = ".*\\[(.*)\\]";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(device);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "UUID";
    }

    private static String execCmd(String cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.redirectErrorStream(true);
            List<String> commands = new ArrayList<>();
            commands.add("/bin/sh");
            commands.add("-c");
            commands.add(cmd);
            builder.command(commands);
            Process process = builder.start();
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                return line;
            }
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return "";
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int getRandomIndexInList(List list){
        Random random = new Random();
        return random.nextInt(list.size());
    }
}
