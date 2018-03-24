package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LanguageUtils {
    public static final List<String> PROPERTY_LIST = Collections.unmodifiableList(
            Arrays.asList("uid","active","defaultCurrency"));

    private static final Logger log = AppLogger.getLogger(LanguageUtils.class.getName() );

    public static JSONArray getLanguageJSONArray()throws Exception{
        return TextalkApiClient.list(TexTalkEntity.LANGUAGE);
    }

    public static List<String> getLanguageList()throws Exception{

        List<String> languageList=new ArrayList<String>();
        JSONArray languageJson=TextalkApiClient.list(TexTalkEntity.LANGUAGE);

        for (Object language:languageJson) {
            JSONObject l=(JSONObject)language;
            languageList.add(l.get("uid").toString());
        }
        return languageList;
    }
}
