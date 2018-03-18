package net.cloudcentrik.textalk;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static jdk.nashorn.internal.parser.TokenType.TRUE;
import static net.cloudcentrik.textalk.TextTalkUtils.log;

/**
 * textalk api testing
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Connecting to textalk api");
        try {

            TextTalkUtils.loadConfigurations();
            //TextTalkUtils.getToken();
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

            //response
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

            JSONObject articleJson= Article.createArticle(article);

            response=TextalkApiClient.validate(TexTalkEntity.ARTICLE,articleJson);
            //response=TextalkApiClient.create(TexTalkEntity.ARTICLE,articleJson,fields);
            response=TextalkApiClient.set(TexTalkEntity.ARTICLE,155447326,articleJson);

            //testProperty();



        } catch (Exception e) {

            System.err.println(e.getMessage());

        } finally {

        }
    }

    private static void testProperty()throws Exception{

        //AppPropertyUtils.updateProperty("LAST_UPDATE", TextTalkUtils.getCurrentTime());
        Map<String,String> propertyMap=AppPropertyUtils.getPropertyMap();
        log(propertyMap.get("LAST_UPDATE"));

        TextTalkUtils.isUpdateToken();

        //Date lastUpdate=new Date("");
    }



}
