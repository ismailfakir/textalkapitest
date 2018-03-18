package net.cloudcentrik.textalk;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionOptions;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static net.cloudcentrik.textalk.TextTalkUtils.log;

public class TextalkApiClient {

    public static String getArticleList() throws Exception{
        String responseString=null;

        URL serverURL = new URL(TextTalkUtils.REQUEST_URL);
        JSONRPC2Session mySession = new JSONRPC2Session(serverURL);

        // Construct new request
        String method = "Article.list";
        String requestID = "2";
        //positional params
        final List<Object> params =new ArrayList<Object>();
        String p[]={"uid", "name"};
        params.add(null);

        //json
        JSONObject searchQuery=new JSONObject();
        searchQuery.put("term","FIIIN produkt");
        searchQuery.put("relevance",100);
        JSONObject search=new JSONObject();
        search.put("search",searchQuery);

        //param map
        Map<String,Object> objectMap=new LinkedHashMap<String,Object>();
        objectMap.put("limit",4);
        objectMap.put("sort","uid");
        objectMap.put("offset",0);

        objectMap.put("filters",search);

        /*JSONObject test=new JSONObject(objectMap);
        log("inside api client");
        log(test.toJSONString());*/

        params.add(objectMap);

        JSONRPC2Request request = new JSONRPC2Request(method,params,requestID);

        // Send request
        JSONRPC2Response response = null;

        response = mySession.send(request);


        // Print response result / error
        if (response.indicatesSuccess()){
            JSONObject r=response.toJSONObject();

            System.out.println( r.toString(JSONStyle.NO_COMPRESS));
            responseString=r.toString(JSONStyle.NO_COMPRESS);

        } else{
            System.out.println(response.getError().getMessage());
            responseString=response.getError().getMessage();

        }

        return responseString;
    }

    private static String texTalkRequest(String method,List<Object> params,String id)throws Exception{
        String responseString=null;

        URL serverURL = new URL(TextTalkUtils.REQUEST_URL);

        //options
        JSONRPC2SessionOptions options=new JSONRPC2SessionOptions();
        options.setConnectTimeout(3000); //3 second
        options.setReadTimeout(1000); // 1 second
        options.setRequestContentType("application/json-rpc");
        options.setAllowedResponseContentTypes(new String[]{"application/json"});
        options.parseNonStdAttributes(true);

        JSONRPC2Session session = new JSONRPC2Session(serverURL);
        session.setOptions(options);


        JSONRPC2Request request = new JSONRPC2Request(method,params,id);


        JSONRPC2Response response=session.send(request);


        if (response.indicatesSuccess()){
            responseString=response.toJSONString();

        } else{
            responseString=response.getError().getMessage();
        }

        System.out.println(responseString);

        return responseString;
    }

    /*get schema*/
    public static String getSchema(TexTalkEntity entity) throws Exception{
        String response=null;
        List<Object> params=new ArrayList<Object>();
        if(entity.equals(TexTalkEntity.LANGUAGE)){
            params.add("sv");
            response=texTalkRequest(entity.toString()+".getSchema",params,"getSchema");
        }else if(entity.equals(TexTalkEntity.ARTICLE)){
            //params.add();
            response=texTalkRequest(entity.toString()+".getSchema",params,"getSchema");
        }

        else {
            response=texTalkRequest(entity.toString()+".getSchema",null,"getSchema");
        }

        return response;
    }

    public static String getList(TexTalkEntity entity, JSONArray fieldsArray,Map<String,Object> selectorMAp) throws Exception{
        String response=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(fieldsArray);
        params.add(selectorMAp);

        String method = entity.toString()+".list";
        String requestID = "list";

        response=texTalkRequest(method,params,requestID);

        return response;
    }

    public static String get(TexTalkEntity entity, int id,JSONArray fieldsArray) throws Exception{
        String response=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(id);
        params.add(fieldsArray);

        String method = entity.toString()+".get";
        String requestID = "get";

        response=texTalkRequest(method,params,requestID);

        return response;
    }

    public static String create(TexTalkEntity entity,JSONObject jsonObject,JSONArray fieldsArray) throws Exception{
        String response=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(null);
        params.add(jsonObject);
        //params.add(fieldsArray);

        String method = entity.toString()+".create";
        String requestID = "create";

        response=texTalkRequest(method,params,requestID);

        return response;
    }

    public static String set(TexTalkEntity entity,int id,JSONObject jsonObject) throws Exception{
        String response=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(id);
        params.add(jsonObject);

        String method = entity.toString()+".set";
        String requestID = "set";

        response=texTalkRequest(method,params,requestID);

        return response;
    }

    public static String validate(TexTalkEntity entity,JSONObject jsonObject) throws Exception{
        String response=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(null);
        params.add(jsonObject);

        String method = entity.toString()+".validate";
        String requestID = "validate";

        response=texTalkRequest(method,params,requestID);

        return response;
    }

    public static String getArticleListTest(TexTalkEntity entity) throws Exception{
        String responseString=null;


        // Construct new request
        String method = entity.toString()+".list";//method
        String requestID = "2";//request id
        final List<Object> params =new ArrayList<Object>();//positional params



        //json
        JSONObject searchQuery=new JSONObject();
        searchQuery.put("min",0);
        searchQuery.put("max",200);
        JSONObject search=new JSONObject();
        search.put("/price/current/SEK",searchQuery);

        //param map
        Map<String,Object> objectMap=new LinkedHashMap<String,Object>();
        objectMap.put("limit",4);
        objectMap.put("sort","uid");
        objectMap.put("offset",0);
        objectMap.put("filters",search);

        //test
        Map<String,Object> testQueryMap=new LinkedHashMap<String,Object>();
        testQueryMap.put("min",0);
        testQueryMap.put("max",200);

        Map<String,Object> testParamMap=ArticleUtils.getListSelector(4,0,"uid","/price/current/SEK",testQueryMap);

        //adding method params
        params.add(ArticleUtils.getARticleFieldsArray());//fields
        //params.add(objectMap);//selector
        params.add(testParamMap);//selector

        //params.add(ArticleUtils.getListSelector(10,0,"uid",null));


        responseString=texTalkRequest(method,params,requestID);


        return responseString;
    }


}
