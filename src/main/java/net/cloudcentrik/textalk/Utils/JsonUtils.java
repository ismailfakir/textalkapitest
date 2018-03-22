package net.cloudcentrik.textalk.Utils;

import net.minidev.json.JSONObject;

public class JsonUtils {

    public static JSONObject getJsonObject(String key, String value){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(key,value);
        return jsonObject;
    }
}
