package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * textalk api testing
 */
public class App {

    private static final Logger log = AppLogger.getLogger( App.class.getName() );


    public static void main(String[] args) {

        try {

            //AppPropertyUtils.updateProperty("LAST_UPDATE",TextTalkUtils.getCurrentTime());
            TextTalkUtils.loadConfigurations();
            //TextTalkUtils.getToken();
            //testProperty();
            //testRequest();
            //testCountRequest();
            //testGetSchemaRequest();
            //testGetLanguageRequest();
            testArticleGroup();

        } catch (Exception e) {

            System.err.println(e.getMessage());

        } finally {

        }
    }

    private static void testProperty()throws Exception{

        AppPropertyUtils.getPropertyMap();
        log.info(AppPropertyUtils.getProperty("BASE_URL"));
    }

    private static void testRequest()throws Exception{

        //request
        String request="{\"jsonrpc\":\"2.0\",\"method\":\"Language.count\",\"params\":[{\"/active\": true}],\"id\":1}";
        //response
        String response=null;

        response=TextalkApiClient.texTalkStringRequest(request);

        log.info(response);

        //TextalkApiClient.getArticleList();
        //TextalkApiClient.getSchema(TexTalkEntity.DEVEVERY_METHOD);
        //TextalkApiClient.getSchema(TexTalkEntity.ARTICLE);

        //Map<String,Object> filterMap=new LinkedHashMap<String, Object>();
        //filterMap.put("equals",true);
        //filterMap.put("term","FIIIN produkt");
        //filterMap.put("relevance",100);
        //filterMap.put("startsWith","An");
        //filterMap.put("in",new int[]{1,2,3});

        //JSONObject jt=new JSONObject(ArticleUtils.getListSelector(4,0,"uid","search",filterMap));
        //log(jt.toJSONString());

        //log(TextalkApiClient.getArticleListTest(TexTalkEntity.ARTICLE));

            /*//response
            String response=null;

            JSONArray fields=ArticleUtils.getARticleFieldsArray();
            Map<String,Object> testQueryMap=new LinkedHashMap<String,Object>();
            testQueryMap.put("equals","Standard");

            String selectorName="/type";

            //Map<String,Object> testSelectorMap=ArticleUtils.getDefaultListSelector(selectorName,testQueryMap);

            //response=TextalkApiClient.getList(TexTalkEntity.ARTICLE,fields,testSelectorMap);

            //response=TextalkApiClient.get(TexTalkEntity.ARTICLE,155447326,fields);

            Article article=new Article();
            article.setArticleNumber("10001");
            article.setEan("879096234");
            article.setSku("8342766777");
            article.setName("A TEST ARTICLE");
            article.setDescription("A TEST ARTICLE FROM JSON RPC 2");
            article.setType("Standard");
            article.setWeight(2000);

            JSONObject articleJson= Article.createArticle(article);*/

        //response=TextalkApiClient.validate(TexTalkEntity.ARTICLE,articleJson);
        //response=TextalkApiClient.create(TexTalkEntity.ARTICLE,articleJson,fields);
        //response=TextalkApiClient.set(TexTalkEntity.ARTICLE,155447326,articleJson);

        //JSONObject responseJson=TextalkApiClient.getArticleListTest(TexTalkEntity.ARTICLE);
        //responseJson.get("vatRate");
        //log(responseJson.toJSONString());
        //JSONRPC2Response respIn = JSONRPC2Response.parse(response);

        //JSONObject responseJson=(JSONObject)respIn.getResult();

            /*Set<String> keys=responseJson.keySet();
            for (String key:keys) {
                log(key+"-------->"+responseJson.get(key).toString());
            }*/
    }

    private static void testCountRequest()throws Exception{

        int response=0;

        Map<String,Object> testQueryMap=new LinkedHashMap<String,Object>();
        testQueryMap.put("min","2013-01-14T15:04:34Z");
        //testQueryMap.put("max",TextTalkUtils.getCurrentTime()+"Z");

        Map<String,Object> selectorMap=ArticleUtils.getBasicFilterMap("/created",testQueryMap);

        response=TextalkApiClient.count(TexTalkEntity.ARTICLE,selectorMap);

        log.info("No of Object = "+response);

    }

    private static void testGetSchemaRequest()throws Exception{

        String response=null;
        response=TextalkApiClient.getSchema(TexTalkEntity.ARTICLE_GROUP);

        log.info(response);
    }

    private static void testGetLanguageRequest()throws Exception{

        Map<String,Object> languageMapFilter=new LinkedHashMap<String, Object>();
        languageMapFilter.put("equals",true);

        Map<String,Object> filterObject=ParamUtils.getBasicFilterMap("active",languageMapFilter);

        String response=null;
        String language = "sv";
        List<Object> params=new ArrayList<Object>();
        JSONObject j=new JSONObject();
        j.put("active",true);
        params.add(j);
        params.add(new String[]{"sv","en"});

        JSONArray jsonResponse=TextalkApiClient.texTalkJJSONArrayRequest("Language.list",params,"get language");

        log.info(jsonResponse.toJSONString());
        ObjectMapper mapper = new ObjectMapper();
        Lanuage l=mapper.readValue(jsonResponse.toJSONString(),Lanuage.class);
        log.info("uid : "+l.getUid());
        log.info("urrency : "+l.getDefaultCurrency());
        log.info("active : "+l.getActive());

    }

    private static void testArticleGroup()throws Exception{

        /*JSONObject articleGroupJson=ArticleGroupUtils.createArticleGroupJson();
        //log.info(articleGroupJson.toJSONString());
        //log.info(articleGroupJson.toJSONString(JSONStyle.MAX_COMPRESS));

        //String validate=TextalkApiClient.validate(TexTalkEntity.ARTICLE_GROUP,articleGroupJson);
       JSONArray p=new JSONArray();
        p.add(null);
        p.add(articleGroupJson);
        JSONRPC2Response r=TextalkApiClient.texTalkBasicRequest("Articlegroup.set",p,"1");

        JSONArray fields=new JSONArray();
        fields.add("uid");
        fields.add("name");

        //String response=TextalkApiClient.create(TexTalkEntity.ARTICLE_GROUP,articleGroupJson,fields);
        //log.info(response);*/

        int uid=5102136;
        String name="cloths";
        //log.info(ArticleGroupUtils.getArticleGroupByUid(uid).toJSONString());
        log.info(ArticleGroupUtils.getArticleGroupByName(name).toJSONString());
        //log.info(ArticleGroupUtils.getAllArticleGroup().toJSONString());
        //log.info(TextalkApiClient.getAll(TexTalkEntity.CUSTOMER,null).toJSONString());

    }

}
