package net.cloudcentrik.textalk;

public enum  TexTalkEntity {

    CUSTOMER("Customer"), ARTICLE("Article"),ORDER("Order"),ARTICLE_RELATION_LIST("ArticleRelationList"),
    LANGUAGE("Language"),ARTICLE_CHOICE("ArticleChoice"),ARTICLE_GROUP("Articlegroup"),ARTICLE_CHOICE_OPTION("ArticleChoiceOption"),
    PAYMENT("Payment"),ORDER_ITEM("OrderItem"),DEVEVERY_METHOD("DeliveryMethod");
    private final String stringValue;
    TexTalkEntity(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
}
