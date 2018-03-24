package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;

import java.io.File;

public class AppConfiguration {

    public static final String BASE_URL="base-url";
    public static final String SHOP_ID="shop-id";
    public static final String DEFAULT_LANGUAGE="default-language-code";
    public static final String USER_NAME="user-name";
    public static final String USER_PASSWORD="user-password";
    public static final String TOKEN="token";
    public static final String LAST_UPDATE="last-token-update-time";

    public static final String CONFIG_FILE = "/home/ismail/Desktop/textalk/app-config.xml";
    private static final Logger log = AppLogger.getLogger(AppConfiguration.class.getName());

    public static String getConfiguration(String key) throws Exception {

        File propertiesFile = new File(CONFIG_FILE);
        if (!propertiesFile.exists()) {
            // file is not exist
            log.warn("configuration file does not exist!");
            return null;
        }

        Configurations configs = new Configurations();
        FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(CONFIG_FILE);
        XMLConfiguration config = builder.getConfiguration();

        return config.getString(key);
    }

    public static void setConfiguration(String key,String value) throws Exception {

        File propertiesFile = new File(CONFIG_FILE);
        if (!propertiesFile.exists()) {
            // file is not exist
            log.warn("configuration file does not exist!");
            return;
        }

        Configurations configs = new Configurations();
        FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(CONFIG_FILE);
        XMLConfiguration config = builder.getConfiguration();
        config.setProperty(key,value);
        builder.save();
    }

    public static String getRequestUrl() throws Exception{
        String url="";

        url=getConfiguration(BASE_URL)+"?webshop="+getConfiguration(SHOP_ID)+"&language="+
                getConfiguration(DEFAULT_LANGUAGE)+"&auth="+getConfiguration(TOKEN);

        return url;
    }

    public static String getBaseUrl() throws Exception{
        String url="";

        url=getConfiguration(BASE_URL)+"?webshop="+getConfiguration(SHOP_ID)+"&language="+
                getConfiguration(DEFAULT_LANGUAGE);

        return url;
    }

}
