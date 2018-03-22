
package net.cloudcentrik.textalk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Language
 * <p>
 * A language
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "uid",
    "active",
    "defaultCurrency"
})
public class Lanuage {

    /**
     * Uid
     * <p>
     * Language UID as IETF language code
     * 
     */
    @JsonProperty("uid")
    @JsonPropertyDescription("Language UID as IETF language code")
    private String uid;
    /**
     * Active
     * <p>
     * Whether the language is active in the shop
     * 
     */
    @JsonProperty("active")
    @JsonPropertyDescription("Whether the language is active in the shop")
    private Boolean active;
    /**
     * Default currency
     * <p>
     * The default currency associated with language
     * 
     */
    @JsonProperty("defaultCurrency")
    @JsonPropertyDescription("The default currency associated with language")
    private String defaultCurrency;

    /**
     * Uid
     * <p>
     * Language UID as IETF language code
     * 
     */
    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    /**
     * Uid
     * <p>
     * Language UID as IETF language code
     * 
     */
    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Active
     * <p>
     * Whether the language is active in the shop
     * 
     */
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    /**
     * Active
     * <p>
     * Whether the language is active in the shop
     * 
     */
    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Default currency
     * <p>
     * The default currency associated with language
     * 
     */
    @JsonProperty("defaultCurrency")
    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    /**
     * Default currency
     * <p>
     * The default currency associated with language
     * 
     */
    @JsonProperty("defaultCurrency")
    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

}
