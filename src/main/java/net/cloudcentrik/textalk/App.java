package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.cloudcentrik.textalk.Utils.JsonUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.*;


/**
 * textalk api testing
 */
public class App {

    private static final Logger log = AppLogger.getLogger(App.class.getName());


    public static void main(String[] args) {

        try {

            TextTalkUtils.loadConfigurations();
            //TextTalkUtils.getToken();
            testLanguageRequest();

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

    private static void testLanguageRequest()throws Exception{

        /*Map<String,Object> languageMapFilter=new LinkedHashMap<String, Object>();
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
        log.info("active : "+l.getActive());*/

        //log.info(LanguageUtils.getLanguageList().toJSONString());
        List<String> language=LanguageUtils.getLanguageList();
        log.info(language.toString());

    }

    private static void testArticleGroup()throws Exception{

        int uid=5104118;
        String name="cloths";
        //log.info(ArticleGroupUtils.getArticleGroupByUid(uid).toJSONString());
        //log.info(ArticleGroupUtils.getArticleGroupByName(name).toJSONString());
        //log.info(ArticleGroupUtils.getAllArticleGroup().toJSONString());
        //log.info(TextalkApiClient.getAll(TexTalkEntity.CUSTOMER,null).toJSONString());

        /*Map<String,String> lan=new HashMap<String, String>();
        lan.put("sv","Datorkomponenter");
        lan.put("en","Computer Components");
        JSONObject jsonObject=ArticleGroupUtils.createArticleGroupJson(lan,lan,lan,false);
        log.info(ArticleGroupUtils.addNewArticleGroup(jsonObject).toJSONString());*/

        //lan.put("en","All kind of books");

        //JSONObject jsonObject=JsonUtils.wrapInLanguage("description",lan);
        //log.info(ArticleGroupUtils.updateArticleGroup(uid,jsonObject).toJSONString());
        log.info(ArticleGroupUtils.getArticleGroupByUid(uid).toJSONString());


    }

}
