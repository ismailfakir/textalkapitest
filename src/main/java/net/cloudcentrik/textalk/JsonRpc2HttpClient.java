package net.cloudcentrik.textalk;

import java.util.*;

public class JsonRpc2HttpClient {

    //{"jsonrpc": "2.0", "method": "subtract", "params": {"minuend": 42, "subtrahend": 23}, "id": 3}
    public static void main(String[] args) {
        System.out.println("Connecting to textalk api");
        /*try {

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(TextTalkUtils.REQUEST_URL);


            //search query
            JSONObject search=new JSONObject();
            search.put("term","produkt");
            search.put("relevance",100);
            JSONObject searchQuery=new JSONObject();
            searchQuery.put("search",search);

            //fields array
            JSONArray fields=new JSONArray();
            fields.put("uid");
            fields.put("name");
            fields.put("ean");
            fields.put("sku");
            fields.put("price");
            fields.put("unit");


            //query map
            Map<String,Object> queryMap=new LinkedHashMap<String,Object>();
            //queryMap.put("filters",searchQuery);
            queryMap.put("filters",null);
            queryMap.put("limit",40);

            //param list
            final List<Object> params =new ArrayList<Object>();
            params.add(fields);
            params.add(queryMap);


            JSONObject jsonObject=new JSONObject();
            jsonObject.put("jsonrpc","2.0");
            jsonObject.put("method","Article.list");
            jsonObject.put("params",params);
            jsonObject.put("id",1);


            System.out.println(jsonObject.toString());

            StringEntity entity = new StringEntity(jsonObject.toString());

            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);

            String str=EntityUtils.toString(response.getEntity(), "UTF-8");

            System.out.println(str);

            client.close();

            } catch (Exception e) {

                System.err.println(e.getMessage());

            }
            finally {

            }
            */
    }

    /*public static String post(String url, Map<String, Object> params) {

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    Object object = params.get(key);
                    nvps.add(new BasicNameValuePair(key, object==null?null:object.toString()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }*/


}
