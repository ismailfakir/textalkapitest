package net.cloudcentrik.textalk;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static net.cloudcentrik.textalk.TextTalkUtils.log;

public class AppPropertyUtils {
    public static final String propertyFileName="/home/ismail/Desktop/textalk/app.properties";

    public static Map<String,String> getPropertyMap() throws Exception{
        Map<String,String> propertyMap=new HashMap<String, String>();

        InputStream in = new FileInputStream(propertyFileName);
        Properties prop = new Properties();
        prop.load(in);

        propertyMap.put("USER_NAME",prop.getProperty("USER_NAME"));
        propertyMap.put("USER_PASSWORD",prop.getProperty("USER_PASSWORD"));
        propertyMap.put("BASE_URL",prop.getProperty("BASE_URL"));
        propertyMap.put("TOKEN",prop.getProperty("TOKEN"));
        propertyMap.put("LAST_UPDATE",prop.getProperty("LAST_UPDATE"));

        in.close();

        //log(propertyMap.toString());

        return propertyMap;
    }

    public static void updateProperty(String key,String value) throws Exception{
        Map<String,String> propertyMap=getPropertyMap();
        OutputStream out = new FileOutputStream(propertyFileName);
        Properties properties = new Properties();
        if(propertyMap.containsKey(key)){
            propertyMap.put(key,value);
        }

        for(String mapKey:propertyMap.keySet()) {
            properties.setProperty(mapKey,propertyMap.get(mapKey));
        }

        properties.store(out, "aplication properties");
        out.close();
    }

    public static String getProperty(String key) throws Exception{

        Map<String,String> propertyMap=getPropertyMap();
        if(propertyMap.containsKey(key)){
            return propertyMap.get(key);
        }else{
            return null;
        }
    }

}
