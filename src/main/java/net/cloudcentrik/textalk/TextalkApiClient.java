package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
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


public class TextalkApiClient {

    private static final Logger log = AppLogger.getLogger(TextalkApiClient.class.getName());

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

    public static JSONRPC2Response texTalkBasicRequest(String method,List<Object> params,String id)throws Exception{

        //url
        URL serverURL = new URL(TextTalkUtils.REQUEST_URL);
        JSONRPC2Session session = new JSONRPC2Session(serverURL);
        session.setOptions(getSessionOptions());
        session.setRawResponseInspector(new TextalkResponseInspector());
        //request
        JSONRPC2Request request = new JSONRPC2Request(method,params,id);
        //response
        JSONRPC2Response response=session.send(request);

        return response;
    }

    /**
     * Return JSONObject from text talk json rpc request
     * @param method
     * @param params
     * @param id
     * @return
     * @throws Exception
     */
    public static JSONObject texTalkJSONObjectRequest(String method,List<Object> params,String id)throws Exception{
        JSONObject responseJson=null;

        //url
        URL serverURL = new URL(TextTalkUtils.REQUEST_URL);
        JSONRPC2Session session = new JSONRPC2Session(serverURL);
        session.setOptions(getSessionOptions());
        //request
        JSONRPC2Request request = new JSONRPC2Request(method,params,id);
        //response
        JSONRPC2Response response=session.send(request);

        if (response.indicatesSuccess()){
            responseJson=(JSONObject)response.getResult();
        } else{
            log.warn(response.getError().toJSONObject().toJSONString());
        }

        return responseJson;
    }

    public static JSONArray texTalkJJSONArrayRequest(String method,List<Object> params,String id)throws Exception{
        JSONArray responseJson=null;

        //url
        URL serverURL = new URL(TextTalkUtils.REQUEST_URL);
        JSONRPC2Session session = new JSONRPC2Session(serverURL);
        session.setOptions(getSessionOptions());
        //request
        JSONRPC2Request request = new JSONRPC2Request(method,params,id);
        //response
        JSONRPC2Response response=session.send(request);

        if (response.indicatesSuccess()){
            responseJson=(JSONArray)response.getResult();
        } else{
            log.warn(response.getError().toJSONObject().toJSONString());
        }

        return responseJson;
    }


    public static String texTalkRequest(String method,List<Object> params,String id)throws Exception{
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

        log.info(request.toJSONObject().toJSONString());


        JSONRPC2Response response=session.send(request);



        if (response.indicatesSuccess()){
            responseString=response.toJSONString();

        } else{
            responseString=response.getError().getMessage();
        }

        System.out.println(responseString);

        return responseString;
    }


    public static String texTalkStringRequest(String requestString)throws Exception{
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


        JSONRPC2Request request = JSONRPC2Request.parse(requestString);


        JSONRPC2Response response=session.send(request);


        if (response.indicatesSuccess()){
            responseString=response.toJSONString();

        } else{
            responseString=response.getError().getMessage();
        }

        return responseString;
    }

    public static JSONObject texTalkJSONRequest(String method,List<Object> params,String id)throws Exception{
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

        return (JSONObject)response.getResult();
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
        params.add(jsonObject);
        //params.add(true);
        params.add(fieldsArray);

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

    public static int count(TexTalkEntity entity,Map<String,Object> selectorMAp) throws Exception{
        String response=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(selectorMAp);

        String method = entity.toString()+".count";
        String requestID = "count id";

        response=texTalkRequest(method,params,requestID);

        JSONRPC2Response jsonrpc2Response=JSONRPC2Response.parse(response);
        int count=Integer.parseInt(jsonrpc2Response.getResult().toString());

        return count;
    }

    public static JSONObject getArticleListTest(TexTalkEntity entity) throws Exception{
        JSONObject responseJson=null;


        // Construct new request
        String method = entity.toString()+".list";//method
        String requestID = "test request";//request id
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


        responseJson=texTalkJSONRequest(method,params,requestID);


        return responseJson;
    }

    /**
     *create default session options
     *
     */
    public static JSONRPC2SessionOptions getSessionOptions(){
        JSONRPC2SessionOptions options=new JSONRPC2SessionOptions();
        options.setConnectTimeout(3000); //3 second
        options.setReadTimeout(1000); // 1 second
        options.setRequestContentType("application/json-rpc");
        options.setAllowedResponseContentTypes(new String[]{"application/json"});
        options.parseNonStdAttributes(true);
        return options;
    }

    /**
     * get list of Object
     * @param entity
     * @param propertyList
     * @return
     * @throws Exception
     */
    public static JSONArray getAll(TexTalkEntity entity,List<String> propertyList)throws Exception{
        JSONArray jsonResponse=null;

        //sort
        JSONObject sortObject=new JSONObject();
        sortObject.put("sort","uid");

        //params
        JSONArray params=new JSONArray();
        params.add(propertyList);
        params.add(sortObject);


        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest(entity.toString()+".list",params,"getAll");

        if(response.indicatesSuccess()){
            //log.info(response.toJSONObject().toJSONString());
            jsonResponse=(JSONArray)response.getResult();
        }else{
            log.error(response.toJSONObject().toJSONString());
        }
        return jsonResponse;
    }

    /**
     * List all object
     * @param entity
     * @return
     * @throws Exception
     */
    public static JSONArray list(TexTalkEntity entity) throws Exception{
        JSONArray jsonResponse=null;

        final List<Object> params =new ArrayList<Object>();
        params.add(true);
        params.add(true);

        JSONRPC2Response response=TextalkApiClient.texTalkBasicRequest(entity.toString()+".list",params,"getAll");

        if(response.indicatesSuccess()){
            jsonResponse=(JSONArray)response.getResult();
        }else{
            log.error(response.toJSONObject().toJSONString());
        }
        return jsonResponse;
    }


}
