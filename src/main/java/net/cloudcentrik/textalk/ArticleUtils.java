package net.cloudcentrik.textalk;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArticleUtils {

    public static JSONArray getARticleFieldsArray(){

        JSONArray fields=new JSONArray();
        fields.add("ean");
        fields.add("name");
        fields.add("price");
        fields.add("sku");
        fields.add("uid");
        fields.add("unit");
        fields.add("vatIsIncluded");
        fields.add("vatRate");
        fields.add("weight");
        fields.add("articleNumber");
        fields.add("description");
        fields.add("stock");
        fields.add("type");
        fields.add("articlegroup");
        fields.add("attributes");
        fields.add("hidden");
        fields.add("images");
        fields.add("isBuyable");
        fields.add("url");
        fields.add("presentationOnly");
        fields.add("choiceOptions");
        fields.add("choiceOptionPrices");
        fields.add("choices");

        return fields;
    }

    public static Map<String,Object> getListSelector(int limit, int offset, String sort, String selectorName, final Map<String,Object> filterMap){

        //selector map
        Map<String,Object> selectorMap=new LinkedHashMap<String,Object>();

        //filter fields
        JSONObject filterFields=new JSONObject();
        for (String key:filterMap.keySet()) {
            filterFields.put(key,filterMap.get(key));
        }

        JSONObject filterObject=new JSONObject();
        filterObject.put(selectorName,filterFields);

        selectorMap.put("limit",limit);
        selectorMap.put("sort",sort);
        selectorMap.put("offset",offset);
        selectorMap.put("filters",filterObject);

        //print the selector
        //log("LIST SELECTOR :\n"+(new JSONObject(selectorMap).toJSONString())+"\n");

        return selectorMap;
    }

    public static Map<String,Object> getDefaultListSelector(String selectorName, final Map<String,Object> filterMap){

        return getListSelector(500,0,"uid",selectorName,filterMap);
    }

    /* get a basic filter object */
    public static Map<String,Object> getBasicFilterMap(String selectorName,final Map<String,Object> filterObjectMap){

        Map<String,Object> filterMap=new LinkedHashMap<String,Object>();

        JSONObject filterFields=new JSONObject();
        for (String key:filterObjectMap.keySet()) {
            filterFields.put(key,filterObjectMap.get(key));
        }

        filterMap.put(selectorName,filterFields);


        return filterMap;
    }
}
