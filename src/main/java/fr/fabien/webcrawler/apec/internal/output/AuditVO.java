package fr.fabien.webcrawler.apec.internal.output;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"dateModification"
})
public class AuditVO {

@JsonProperty("dateModification")
private String dateModification;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap();

@JsonProperty("dateModification")
public String getDateModification() {
return dateModification;
}

@JsonProperty("dateModification")
public void setDateModification(String dateModification) {
this.dateModification = dateModification;
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

