package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.*;

public class ArticleGroupUtils {

    public static final List<String> PROPERTY_LIST = Collections.unmodifiableList(
            Arrays.asList("baseName", "children", "description", "hidden","image","metaDescription",
                    "metaKeywords","name","pageItems","pageTitle","parent","uid","url"));

    private static final Logger log = AppLogger.getLogger( App.class.getName() );

    public static JSONObject wrapeInLanguage(Map<String,String> laguageMap){
        JSONObject jsonObject=new JSONObject();
        jsonObject.putAll(laguageMap);
        return jsonObject;
    }

    public static JSONObject getJsonObject(String key,String value){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(key,value);
        return jsonObject;
    }

    public static JSONObject createArticleGroupJson(){
        //readonly value should be omitted
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("baseName",getJsonObject("sv","example Article group"));
        //jsonObject.put("children",new JSONArray());
        //jsonObject.put("description",getJsonObject("sv","example Article group descriptions"));
        jsonObject.put("hidden",false);
        //jsonObject.put("image",null);
        jsonObject.put("metaDescription",getJsonObject("sv","metaDescription"));
        //jsonObject.put("metaKeywords",getJsonObject("sv","metaKeywords"));
        jsonObject.put("name",getJsonObject("sv","Electronics"));
        //jsonObject.put("pageItems",new JSONArray());
        //jsonObject.put("pageTitle",getJsonObject("sv","pageTitle Electronics"));
        jsonObject.put("parent",null);
        //jsonObject.put("uid",null);
        //jsonObject.put("url",getJsonObject("sv",""));
        return jsonObject;
    }

    public static JSONObject createArticleGroup(ArticleGroup articleGroup){
        //readonly value should be omitted
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("baseName",getJsonObject("sv","example Article group"));
        //jsonObject.put("children",new JSONArray());
        //jsonObject.put("description",getJsonObject("sv","example Article group descriptions"));
        jsonObject.put("hidden",false);
        //jsonObject.put("image",null);
        jsonObject.put("metaDescription",getJsonObject("sv","metaDescription"));
        //jsonObject.put("metaKeywords",getJsonObject("sv","metaKeywords"));
        jsonObject.put("name",getJsonObject("sv","Electronics"));
        //jsonObject.put("pageItems",new JSONArray());
        //jsonObject.put("pageTitle",getJsonObject("sv","pageTitle Electronics"));
        jsonObject.put("parent",null);
        //jsonObject.put("uid",null);
        //jsonObject.put("url",getJsonObject("sv",""));
        return jsonObject;
    }

    private static Boolean addArticleGroup(JSONObject articleGroupJson)throws Exception{

        //params
        JSONArray param=new JSONArray();
        param.add(null);
        param.add(articleGroupJson);

        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest("Articlegroup.set",param,"1");
        if(response.indicatesSuccess()){
            log.info(response.toJSONObject().toJSONString());
            return true;
        }else{
            log.error(response.toJSONObject().toJSONString());
            return false;
        }

    }

    /**
     * find a ArticleGroup by uid
     * @param uid
     * @return JSONObject
     * @throws Exception
     */
    public static JSONObject getArticleGroupByUid(int uid)throws Exception{
        JSONObject jsonResponse=null;

        //params
        JSONArray param=new JSONArray();
        param.add(uid);
        param.add(ArticleGroupUtils.PROPERTY_LIST);

        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest("Articlegroup.get",param,"1");

        if(response.indicatesSuccess()){
            log.info(response.toJSONObject().toJSONString());
            jsonResponse=(JSONObject)response.getResult();
        }else{
            log.error(response.toJSONObject().toJSONString());
        }
        return jsonResponse;
    }

    /**
     * find a ArticleGroup by name
     * @param name
     * @return JSONObject
     * @throws Exception
     */
    public static JSONObject getArticleGroupByName(String name)throws Exception{
        JSONObject jsonResponse=null;

        //params
        JSONArray params=new JSONArray();

        params.add(ArticleGroupUtils.PROPERTY_LIST);

        Map<String,String> filterMap=new HashMap<String,String>();
        filterMap.put("equals",name);

        params.add(ParamUtils.getFiltersObject("/name/sv",filterMap));

        log.debug(params.toJSONString());

        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest("Articlegroup.list",params,"1");

        if(response.indicatesSuccess()){
            log.info(response.toJSONObject().toJSONString());
            jsonResponse=(JSONObject)response.getResult();
        }else{
            log.error(response.toJSONObject().toJSONString());
        }
        return jsonResponse;
    }

    public static JSONArray getAllArticleGroup()throws Exception{
        return TextalkApiClient.getAll(TexTalkEntity.ARTICLE_GROUP,ArticleGroupUtils.PROPERTY_LIST);
    }


}
