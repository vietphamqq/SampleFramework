package utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.FIRST)
@Sources({"classpath:env.${env}.properties"
})
public interface Environments extends Config {
    @DefaultValue("BS default")
    @Key("BS.USER")
    String BS_USER();

    @DefaultValue("Default Key")
    @Key("BS.KEY")
    String BS_KEY();

    @Key("DESKTOP_WEB.URL")
    String WEB_URL();

    @Key("location")
    @DefaultValue("lc")
    String RUN_LOCATION();

    @Key("page.time_out")
    @DefaultValue("30")
    long TIME_OUTS();

    @Key("DESKTOP_WEB.ADMIN_EMAIL")
    String WEB_ADMIN_EMAIL();

    @Key("DESKTOP_WEB.PASSWORD")
    String WEB_PASSWORD();

    @Key("DESKTOP_WEB.USER_NAME")
    String USER_NAME();

    @Key("MYSQL.HOST")
    String MYSQL_HOST();

    @Key("MYSQL.PORT")
    String MYSQL_PORT();

    @Key("MYSQL.USER_NAME")
    String MYSQL_USERNAME();

    @Key("MYSQL.PASSWORD")
    String MYSQL_PASSWORD();

    @Key("TIMEZONE")
    String TIMEZONE();

    @Key("MONGO.USER_NAME")
    String MONGO_USER();

    @Key("MONGO.PASSWORD")
    String MONGO_PASSWORD();

    @Key("MONGO.HOST")
    String MONGO_HOST();

    @Key("MONGO.PORT")
    int MONGO_PORT();

    @Key("COUNTRY")
    String COUNTRY();

    @Key("MOBILE.androidPackage")
    String ANDROID_PACKAGE();

    @Key("MOBILE.android_activity")
    String ANDROID_ACTIVITY();

    @Key("MOBILE.iOSBundle")
    String IOS_BUNDLE();

    @Key("IOS.APP_ID")
    String IOS_APP_ID();

    @Key("IOS.LOCAL_APP_FILE")
    String IOS_LOCAL_APP_FILE();

    @Key("ANDROID.APP_ID")
    String ANDROID_APP_ID();

    @Key("ANDROID.LOCAL_APP_FILE")
    String ANDROID_LOCAL_APP_FILE();

    @Key("MOBILE.EMAIL")
    String MOBILE_EMAIL();

    @Key("MOBILE.PASS")
    String MOBILE_PASSWORD();

    @Key("MOBILE.USERNAME")
    String MOBILE_USER_NAME();

    @Key("COUNTRY.CODE")
    String COUNTRY_CODE();

    @Key("COUNTRY.CURRENCY")
    String COUNTRY_CURRENCY();

    @Key("COUNTRY.SHORT_CURRENCY")
    String COUNTRY_SHORT_CURRENCY();

    @Key("install")
    @DefaultValue("true")
    boolean REQUIRE_INSTALL();

    @Key("API.GATEWAY.URL")
    String API_GATEWAY_BASE_URL();

    @Key("API.GATEWAY.KEY")
    String API_GATEWAY_KEY();

    @Key("API.GATEWAY.INTERNAL")
    String API_GATEWAY_INTERNAL();

    @Key("API.EXTERNAL.URL")
    String API_EXTERNAL_URL();

    @Key("API.EXTERNAL.PORT")
    String API_EXTERNAL_PORT();

    @Key("BS.TIMEZONE")
    String BS_TIMEZONE();

    @Key("BS.BUILD_WEB_NAME")
    String BS_BUILD_WEB();

}
