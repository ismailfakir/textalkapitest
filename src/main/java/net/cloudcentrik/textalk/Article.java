package net.cloudcentrik.textalk;

import net.minidev.json.JSONObject;


public class Article {

    private String articleNumber;
    private String ean;
    private String name;
    private String type;
    private String sku;
    private String description;
    private Integer weight;

    public Article(String articleNumber, String ean, String name, String type, String sku, String description, Integer weight) {
        this.articleNumber = articleNumber;
        this.ean = ean;
        this.name = name;
        this.type = type;
        this.sku = sku;
        this.description = description;
        this.weight = weight;
    }

    public Article() {
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static JSONObject createArticle(Article article){
        JSONObject articleJson=new JSONObject();

        articleJson.put("articleNumber",article.getArticleNumber());
        articleJson.put("name",wrapeInLanguage("sv",article.getName()));
        articleJson.put("description",wrapeInLanguage("sv",article.getDescription()));

        articleJson.put("ean",article.getEan());
        articleJson.put("sku",article.getSku());
        //articleJson.put("type",article.getType());
        articleJson.put("weight",article.getWeight());

        //log(articleJson.toJSONString());

        return articleJson;
    }

    private static JSONObject wrapeInLanguage(String language,String content){
        JSONObject contentInLanguage=new JSONObject();
        contentInLanguage.put(language,content);
        return contentInLanguage;
    }
}
