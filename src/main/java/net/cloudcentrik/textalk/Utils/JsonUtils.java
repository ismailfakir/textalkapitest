package net.cloudcentrik.textalk.Utils;

import net.minidev.json.JSONObject;

import java.util.Map;

public class JsonUtils {

    public static JSONObject getJsonObject(String key, Object value){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(key,value);
        return jsonObject;
    }

    public static JSONObject wrapInLanguage(String property, Map<String,String> languageMap){
        JSONObject jsonObject=new JSONObject();
        jsonObject.putAll(languageMap);
        JSONObject propertyJson=new JSONObject();
        propertyJson.put(property,jsonObject);
        return propertyJson;
    }

    public static JSONObject wrapInLanguage(Map<String,String> languageMap){
        JSONObject jsonObject=new JSONObject();
        jsonObject.putAll(languageMap);
        return jsonObject;
    }
}
