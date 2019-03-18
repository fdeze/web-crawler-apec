
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
@JsonPropertyOrder({ "adresseCodePostal", "adresseNumeroEtVoie", "adresseVille", "idPays", "id" })
public class AdresseOffre {

	@JsonProperty("adresseCodePostal")
	private String adresseCodePostal;
	@JsonProperty("adresseNumeroEtVoie")
	private String adresseNumeroEtVoie;
	@JsonProperty("adresseVille")
	private String adresseVille;
	@JsonProperty("idPays")
	private int idPays;
	@JsonProperty("id")
	private int id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap();

	@JsonProperty("adresseCodePostal")
	public String getAdresseCodePostal() {
		return adresseCodePostal;
	}

	@JsonProperty("adresseCodePostal")
	public void setAdresseCodePostal(String adresseCodePostal) {
		this.adresseCodePostal = adresseCodePostal;
	}

	@JsonProperty("adresseNumeroEtVoie")
	public String getAdresseNumeroEtVoie() {
		return adresseNumeroEtVoie;
	}

	@JsonProperty("adresseNumeroEtVoie")
	public void setAdresseNumeroEtVoie(String adresseNumeroEtVoie) {
		this.adresseNumeroEtVoie = adresseNumeroEtVoie;
	}

	@JsonProperty("adresseVille")
	public String getAdresseVille() {
		return adresseVille;
	}

	@JsonProperty("adresseVille")
	public void setAdresseVille(String adresseVille) {
		this.adresseVille = adresseVille;
	}

	@JsonProperty("idPays")
	public int getIdPays() {
		return idPays;
	}

	@JsonProperty("idPays")
	public void setIdPays(int idPays) {
		this.idPays = idPays;
	}

	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return adresseCodePostal + "," + adresseNumeroEtVoie + ", " + adresseVille + ", " + additionalProperties;
	}

}
