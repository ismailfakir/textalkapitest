package net.cloudcentrik.textalk;

import net.minidev.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParamUtils {
    /* get a basic filter object */
    public static Map<String,Object> getBasicFilterMap(String selectorName, final Map<String,Object> filterObjectMap){

        Map<String,Object> filterMap=new LinkedHashMap<String,Object>();

        JSONObject filterFields=new JSONObject();
        for (String key:filterObjectMap.keySet()) {
            filterFields.put(key,filterObjectMap.get(key));
        }

        filterMap.put(selectorName,filterFields);


        return filterMap;
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

    /**
     * create a filters object
     * @param selectorName
     * @param filterMap
     * @return Map<String,Object>
     */
    public static Map<String,Object> getFiltersObject(String selectorName, final Map<String,String> filterMap){

        //selector map
        Map<String,Object> selectorMap=new LinkedHashMap<String,Object>();

        //filter fields
        JSONObject filterFields=new JSONObject();
        for (String key:filterMap.keySet()) {
            filterFields.put(key,filterMap.get(key));
        }

        JSONObject filterObject=new JSONObject();
        filterObject.put(selectorName,filterFields);

        selectorMap.put("filters",filterObject);

        return selectorMap;
    }

    public static Map<String,Object> getFiltersObject(String selectorName, String key,String value){

        //selector map
        Map<String,Object> selectorMap=new LinkedHashMap<String,Object>();

        JSONObject selectionObject=new JSONObject();
        selectionObject.put(key,value);

        JSONObject filterObject=new JSONObject();
        filterObject.put(selectorName,selectionObject);

        selectorMap.put("filters",filterObject);

        return selectorMap;
    }
}
