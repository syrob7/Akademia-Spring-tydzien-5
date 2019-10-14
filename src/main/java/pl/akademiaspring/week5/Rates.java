package pl.akademiaspring.week5;

import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GBP",
    "CHF",
    "EUR",
    "USD",
    "PLN"
})
public class Rates {

    @JsonProperty("GBP")
    private BigDecimal gBP;
    @JsonProperty("CHF")
    private BigDecimal cHF;
    @JsonProperty("EUR")
    private BigDecimal eUR;
    @JsonProperty("USD")
    private BigDecimal uSD;
    @JsonProperty("PLN")
    private BigDecimal pLN;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("GBP")
    public BigDecimal getGBP() {
        return gBP;
    }

    @JsonProperty("GBP")
    public void setGBP(BigDecimal gBP) {
        this.gBP = gBP;
    }

    @JsonProperty("CHF")
    public BigDecimal getCHF() {
        return cHF;
    }

    @JsonProperty("CHF")
    public void setCHF(BigDecimal cHF) {
        this.cHF = cHF;
    }

    @JsonProperty("EUR")
    public BigDecimal getEUR() {
        return eUR;
    }

    @JsonProperty("EUR")
    public void setEUR(BigDecimal eUR) {
        this.eUR = eUR;
    }

    @JsonProperty("USD")
    public BigDecimal getUSD() {
        return uSD;
    }

    @JsonProperty("USD")
    public void setUSD(BigDecimal uSD) {
        this.uSD = uSD;
    }

    @JsonProperty("PLN")
    public BigDecimal getPLN() {
        return pLN;
    }

    @JsonProperty("PLN")
    public void setPLN(BigDecimal pLN) {
        this.pLN = pLN;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
