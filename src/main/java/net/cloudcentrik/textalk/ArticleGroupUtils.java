package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.cloudcentrik.textalk.Utils.JsonUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.*;

public class ArticleGroupUtils {

    public static final List<String> PROPERTY_LIST = Collections.unmodifiableList(
            Arrays.asList("baseName", "children", "description", "hidden","image","metaDescription",
                    "metaKeywords","name","pageItems","pageTitle","parent","uid","url"));

    private static final Logger log = AppLogger.getLogger( App.class.getName() );


    public static JSONObject createArticleGroupJson(){
        //readonly value should be omitted
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("baseName",JsonUtils.getJsonObject("sv","example Article group"));
        //jsonObject.put("children",new JSONArray());
        //jsonObject.put("description",getJsonObject("sv","example Article group descriptions"));
        jsonObject.put("hidden",false);
        //jsonObject.put("image",null);
        jsonObject.put("metaDescription",JsonUtils.getJsonObject("sv","metaDescription"));
        //jsonObject.put("metaKeywords",getJsonObject("sv","metaKeywords"));
        jsonObject.put("name",JsonUtils.getJsonObject("sv","Electronics"));
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
        jsonObject.put("baseName",JsonUtils.getJsonObject("sv","example Article group"));
        //jsonObject.put("children",new JSONArray());
        //jsonObject.put("description",getJsonObject("sv","example Article group descriptions"));
        jsonObject.put("hidden",false);
        //jsonObject.put("image",null);
        jsonObject.put("metaDescription",JsonUtils.getJsonObject("sv","metaDescription"));
        //jsonObject.put("metaKeywords",getJsonObject("sv","metaKeywords"));
        jsonObject.put("name",JsonUtils.getJsonObject("sv","Electronics"));
        //jsonObject.put("pageItems",new JSONArray());
        //jsonObject.put("pageTitle",getJsonObject("sv","pageTitle Electronics"));
        jsonObject.put("parent",null);
        //jsonObject.put("uid",null);
        //jsonObject.put("url",getJsonObject("sv",""));
        return jsonObject;
    }

    public static JSONObject createArticleGroupJson(Map<String,String> baseName,Map<String,String> name,Map<String,String> description,Boolean hidden){
        //readonly value should be omitted
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("baseName", JsonUtils.wrapInLanguage(baseName));
        //jsonObject.put("children",new JSONArray());
        jsonObject.put("description",JsonUtils.wrapInLanguage(description));
        jsonObject.put("hidden",hidden);
        //jsonObject.put("image",null);
        //jsonObject.put("metaDescription",getJsonObject("sv","metaDescription"));
        //jsonObject.put("metaKeywords",getJsonObject("sv","metaKeywords"));
        jsonObject.put("name",JsonUtils.wrapInLanguage(name));
        //jsonObject.put("pageItems",new JSONArray());
        //jsonObject.put("pageTitle",getJsonObject("sv","pageTitle Electronics"));
        jsonObject.put("parent",null);
        //jsonObject.put("uid",null);
        //jsonObject.put("url",getJsonObject("sv",""));
        return jsonObject;
    }

    /**
     * Create a article group Json object
     * @param baseName
     * @param name
     * @param description
     * @param hidden
     * @return
     */
    public static JSONObject createArticleGroupJson(String baseName,String name,String description,Boolean hidden){
        //readonly value should be omitted
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("baseName", JsonUtils.getJsonObject("sv",baseName));
        //jsonObject.put("children",new JSONArray());
        jsonObject.put("description",JsonUtils.getJsonObject("sv",description));
        jsonObject.put("hidden",hidden);
        //jsonObject.put("image",null);
        //jsonObject.put("metaDescription",getJsonObject("sv","metaDescription"));
        //jsonObject.put("metaKeywords",getJsonObject("sv","metaKeywords"));
        jsonObject.put("name",JsonUtils.getJsonObject("sv",name));
        //jsonObject.put("pageItems",new JSONArray());
        //jsonObject.put("pageTitle",getJsonObject("sv","pageTitle Electronics"));
        jsonObject.put("parent",null);
        //jsonObject.put("uid",null);
        //jsonObject.put("url",getJsonObject("sv",""));
        return jsonObject;
    }

    /**
     * Update a article group
     * @param uid
     * @param articleGroupJson
     * @return
     * @throws Exception
     */
    public static JSONObject updateArticleGroup(int uid,JSONObject articleGroupJson)throws Exception{

        JSONObject responseJson=null;

        //params
        JSONArray param=new JSONArray();
        param.add(uid);
        param.add(articleGroupJson);
        param.add(ArticleGroupUtils.PROPERTY_LIST);

        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest("Articlegroup.set",param,"1");
        if(response.indicatesSuccess()){
            //log.info(response.toJSONObject().toJSONString());
            responseJson=(JSONObject) response.getResult();

        }else{
            log.error(response.toJSONObject().toJSONString());

        }
        return responseJson;
    }

    /**
     * Create a new Article group
     * @param articleGroupJson
     * @return
     * @throws Exception
     */
    public static JSONObject addNewArticleGroup(JSONObject articleGroupJson)throws Exception{
        JSONObject jsonResponse=null;

        //params
        JSONArray param=new JSONArray();
        param.add(null);
        param.add(articleGroupJson);
        param.add(ArticleGroupUtils.PROPERTY_LIST);

        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest("Articlegroup.set",param,"1");
        if(response.indicatesSuccess()){
            jsonResponse=(JSONObject)response.getResult();
        }else{
            log.error(response.toJSONObject().toJSONString());
        }
        return jsonResponse;
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

    /**
     * Get list of article group
     * @return
     * @throws Exception
     */
    public static JSONArray getArticleGroupList()throws Exception{
        return TextalkApiClient.getAll(TexTalkEntity.ARTICLE_GROUP,ArticleGroupUtils.PROPERTY_LIST);
    }


}
