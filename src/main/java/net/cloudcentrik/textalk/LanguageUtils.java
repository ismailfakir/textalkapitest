package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LanguageUtils {
    public static final List<String> PROPERTY_LIST = Collections.unmodifiableList(
            Arrays.asList("uid","active","defaultCurrency"));

    private static final Logger log = AppLogger.getLogger(LanguageUtils.class.getName() );
    public static JSONArray getLanguageList()throws Exception{
        return getAllLanguage(TexTalkEntity.LANGUAGE,LanguageUtils.PROPERTY_LIST);
    }

    public static JSONArray getAllLanguage(TexTalkEntity entity,List<String> propertyList)throws Exception{
        JSONArray jsonResponse=null;

        //sort
        JSONObject sortObject=new JSONObject();
        sortObject.put("active","true");

        //params
        JSONArray params=new JSONArray();
        params.add(propertyList);
        //params.add(ParamUtils.getFiltersObject("/defaultCurrency","startsWith","S"));
        params.add(true);



        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest(entity.toString()+".list",params,"getAll");

        if(response.indicatesSuccess()){
            //log.info(response.toJSONObject().toJSONString());
            jsonResponse=(JSONArray)response.getResult();
        }else{
            log.error(response.toJSONObject().toJSONString());
        }
        return jsonResponse;
    }
}
