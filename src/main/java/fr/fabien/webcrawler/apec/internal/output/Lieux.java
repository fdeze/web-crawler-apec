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
"idNomLieu",
"libelleLieu"
})
public class Lieux {

@JsonProperty("idNomLieu")
private int idNomLieu;
@JsonProperty("libelleLieu")
private String libelleLieu;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap();

@JsonProperty("idNomLieu")
public int getIdNomLieu() {
return idNomLieu;
}

@JsonProperty("idNomLieu")
public void setIdNomLieu(int idNomLieu) {
this.idNomLieu = idNomLieu;
}

@JsonProperty("libelleLieu")
public String getLibelleLieu() {
return libelleLieu;
}

@JsonProperty("libelleLieu")
public void setLibelleLieu(String libelleLieu) {
this.libelleLieu = libelleLieu;
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
