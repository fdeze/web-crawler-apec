package fr.fabien.webcrawler.apec.internal.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "adresseOffre", "audit", "comptePersonnelIdNomOrganisation", "datePublication",
		"datePremierePublication", "id", "idEtablissement", "idInterlocuteurDirect", "idNomFonction",
		"idNomNiveauExperience", "idNomSecteurActivite", "idSecteurActiviteParent", "idNomStatut", "idNomTypeContrat",
		"idNomTypePoste", "idNomZoneDeplacement", "idResponsable", "interlocuteurSuiviFonction",
		"interlocuteurSuiviNom", "interlocuteurSuiviPrenom", "intitule", "lieux", "legacyLieux", "logoEtablissement",
		"nombrePostes", "nomCompteEtablissement", "nomInterlocuteur", "numeroOffre", "offreConfidentielle",
		"offreQualifiee", "prenomInterlocuteur", "salaireTexte", "tempsPartiel", "texteHtml", "typeCandidature",
		"offreSalon", "latitude", "longitude", "geolocalisable", "texteHtmlProfil", "texteHtmlEntreprise",
		"idNomCiviliteSuivi", "enseigne", "affichageLogo", "indicateurLm" })
public class ApecOffer {

	@JsonProperty("adresseOffre")
	private AdresseOffre adresseOffre;
	@JsonProperty("audit")
	private Audit audit;
	@JsonProperty("comptePersonnelIdNomOrganisation")
	private int comptePersonnelIdNomOrganisation;
	@JsonProperty("datePublication")
	private String datePublication;
	@JsonProperty("datePremierePublication")
	private String datePremierePublication;
	@JsonProperty("id")
	private int id;
	@JsonProperty("idEtablissement")
	private int idEtablissement;
	@JsonProperty("idInterlocuteurDirect")
	private int idInterlocuteurDirect;
	@JsonProperty("idNomFonction")
	private int idNomFonction;
	@JsonProperty("idNomNiveauExperience")
	private int idNomNiveauExperience;
	@JsonProperty("idNomSecteurActivite")
	private int idNomSecteurActivite;
	@JsonProperty("idSecteurActiviteParent")
	private int idSecteurActiviteParent;
	@JsonProperty("idNomStatut")
	private int idNomStatut;
	@JsonProperty("idNomTypeContrat")
	private int idNomTypeContrat;
	@JsonProperty("idNomTypePoste")
	private int idNomTypePoste;
	@JsonProperty("idNomZoneDeplacement")
	private int idNomZoneDeplacement;
	@JsonProperty("idResponsable")
	private String idResponsable;
	@JsonProperty("interlocuteurSuiviFonction")
	private String interlocuteurSuiviFonction;
	@JsonProperty("interlocuteurSuiviNom")
	private String interlocuteurSuiviNom;
	@JsonProperty("interlocuteurSuiviPrenom")
	private String interlocuteurSuiviPrenom;
	@JsonProperty("intitule")
	private String intitule;
	@JsonProperty("lieux")
	private List<Lieux> lieux = new ArrayList();
	@JsonProperty("legacyLieux")
	private boolean legacyLieux;
	@JsonProperty("logoEtablissement")
	private String logoEtablissement;
	@JsonProperty("nombrePostes")
	private int nombrePostes;
	@JsonProperty("nomCompteEtablissement")
	private String nomCompteEtablissement;
	@JsonProperty("nomInterlocuteur")
	private String nomInterlocuteur;
	@JsonProperty("numeroOffre")
	private String numeroOffre;
	@JsonProperty("offreConfidentielle")
	private boolean offreConfidentielle;
	@JsonProperty("offreQualifiee")
	private boolean offreQualifiee;
	@JsonProperty("prenomInterlocuteur")
	private String prenomInterlocuteur;
	@JsonProperty("salaireTexte")
	private String salaireTexte;
	@JsonProperty("tempsPartiel")
	private boolean tempsPartiel;
	@JsonProperty("texteHtml")
	private String texteHtml;
	@JsonProperty("typeCandidature")
	private String typeCandidature;
	@JsonProperty("offreSalon")
	private boolean offreSalon;
	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;
	@JsonProperty("geolocalisable")
	private boolean geolocalisable;
	@JsonProperty("texteHtmlProfil")
	private String texteHtmlProfil;
	@JsonProperty("texteHtmlEntreprise")
	private String texteHtmlEntreprise;
	@JsonProperty("idNomCiviliteSuivi")
	private int idNomCiviliteSuivi;
	@JsonProperty("enseigne")
	private String enseigne;
	@JsonProperty("affichageLogo")
	private boolean affichageLogo;
	@JsonProperty("indicateurLm")
	private boolean indicateurLm;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap();

	@JsonProperty("adresseOffre")
	public AdresseOffre getAdresseOffre() {
		return adresseOffre;
	}

	@JsonProperty("adresseOffre")
	public void setAdresseOffre(AdresseOffre adresseOffre) {
		this.adresseOffre = adresseOffre;
	}

	@JsonProperty("audit")
	public Audit getAudit() {
		return audit;
	}

	@JsonProperty("audit")
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	@JsonProperty("comptePersonnelIdNomOrganisation")
	public int getComptePersonnelIdNomOrganisation() {
		return comptePersonnelIdNomOrganisation;
	}

	@JsonProperty("comptePersonnelIdNomOrganisation")
	public void setComptePersonnelIdNomOrganisation(int comptePersonnelIdNomOrganisation) {
		this.comptePersonnelIdNomOrganisation = comptePersonnelIdNomOrganisation;
	}

	@JsonProperty("datePublication")
	public String getDatePublication() {
		return datePublication;
	}

	@JsonProperty("datePublication")
	public void setDatePublication(String datePublication) {
		this.datePublication = datePublication;
	}

	@JsonProperty("datePremierePublication")
	public String getDatePremierePublication() {
		return datePremierePublication;
	}

	@JsonProperty("datePremierePublication")
	public void setDatePremierePublication(String datePremierePublication) {
		this.datePremierePublication = datePremierePublication;
	}

	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("idEtablissement")
	public int getIdEtablissement() {
		return idEtablissement;
	}

	@JsonProperty("idEtablissement")
	public void setIdEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	@JsonProperty("idInterlocuteurDirect")
	public int getIdInterlocuteurDirect() {
		return idInterlocuteurDirect;
	}

	@JsonProperty("idInterlocuteurDirect")
	public void setIdInterlocuteurDirect(int idInterlocuteurDirect) {
		this.idInterlocuteurDirect = idInterlocuteurDirect;
	}

	@JsonProperty("idNomFonction")
	public int getIdNomFonction() {
		return idNomFonction;
	}

	@JsonProperty("idNomFonction")
	public void setIdNomFonction(int idNomFonction) {
		this.idNomFonction = idNomFonction;
	}

	@JsonProperty("idNomNiveauExperience")
	public int getIdNomNiveauExperience() {
		return idNomNiveauExperience;
	}

	@JsonProperty("idNomNiveauExperience")
	public void setIdNomNiveauExperience(int idNomNiveauExperience) {
		this.idNomNiveauExperience = idNomNiveauExperience;
	}

	@JsonProperty("idNomSecteurActivite")
	public int getIdNomSecteurActivite() {
		return idNomSecteurActivite;
	}

	@JsonProperty("idNomSecteurActivite")
	public void setIdNomSecteurActivite(int idNomSecteurActivite) {
		this.idNomSecteurActivite = idNomSecteurActivite;
	}

	@JsonProperty("idSecteurActiviteParent")
	public int getIdSecteurActiviteParent() {
		return idSecteurActiviteParent;
	}

	@JsonProperty("idSecteurActiviteParent")
	public void setIdSecteurActiviteParent(int idSecteurActiviteParent) {
		this.idSecteurActiviteParent = idSecteurActiviteParent;
	}

	@JsonProperty("idNomStatut")
	public int getIdNomStatut() {
		return idNomStatut;
	}

	@JsonProperty("idNomStatut")
	public void setIdNomStatut(int idNomStatut) {
		this.idNomStatut = idNomStatut;
	}

	@JsonProperty("idNomTypeContrat")
	public int getIdNomTypeContrat() {
		return idNomTypeContrat;
	}

	@JsonProperty("idNomTypeContrat")
	public void setIdNomTypeContrat(int idNomTypeContrat) {
		this.idNomTypeContrat = idNomTypeContrat;
	}

	@JsonProperty("idNomTypePoste")
	public int getIdNomTypePoste() {
		return idNomTypePoste;
	}

	@JsonProperty("idNomTypePoste")
	public void setIdNomTypePoste(int idNomTypePoste) {
		this.idNomTypePoste = idNomTypePoste;
	}

	@JsonProperty("idNomZoneDeplacement")
	public int getIdNomZoneDeplacement() {
		return idNomZoneDeplacement;
	}

	@JsonProperty("idNomZoneDeplacement")
	public void setIdNomZoneDeplacement(int idNomZoneDeplacement) {
		this.idNomZoneDeplacement = idNomZoneDeplacement;
	}

	@JsonProperty("idResponsable")
	public String getIdResponsable() {
		return idResponsable;
	}

	@JsonProperty("idResponsable")
	public void setIdResponsable(String idResponsable) {
		this.idResponsable = idResponsable;
	}

	@JsonProperty("interlocuteurSuiviFonction")
	public String getInterlocuteurSuiviFonction() {
		return interlocuteurSuiviFonction;
	}

	@JsonProperty("interlocuteurSuiviFonction")
	public void setInterlocuteurSuiviFonction(String interlocuteurSuiviFonction) {
		this.interlocuteurSuiviFonction = interlocuteurSuiviFonction;
	}

	@JsonProperty("interlocuteurSuiviNom")
	public String getInterlocuteurSuiviNom() {
		return interlocuteurSuiviNom;
	}

	@JsonProperty("interlocuteurSuiviNom")
	public void setInterlocuteurSuiviNom(String interlocuteurSuiviNom) {
		this.interlocuteurSuiviNom = interlocuteurSuiviNom;
	}

	@JsonProperty("interlocuteurSuiviPrenom")
	public String getInterlocuteurSuiviPrenom() {
		return interlocuteurSuiviPrenom;
	}

	@JsonProperty("interlocuteurSuiviPrenom")
	public void setInterlocuteurSuiviPrenom(String interlocuteurSuiviPrenom) {
		this.interlocuteurSuiviPrenom = interlocuteurSuiviPrenom;
	}

	@JsonProperty("intitule")
	public String getIntitule() {
		return intitule;
	}

	@JsonProperty("intitule")
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@JsonProperty("lieux")
	public List<Lieux> getLieux() {
		return lieux;
	}

	@JsonProperty("lieux")
	public void setLieux(List<Lieux> lieux) {
		this.lieux = lieux;
	}

	@JsonProperty("legacyLieux")
	public boolean isLegacyLieux() {
		return legacyLieux;
	}

	@JsonProperty("legacyLieux")
	public void setLegacyLieux(boolean legacyLieux) {
		this.legacyLieux = legacyLieux;
	}

	@JsonProperty("logoEtablissement")
	public String getLogoEtablissement() {
		return logoEtablissement;
	}

	@JsonProperty("logoEtablissement")
	public void setLogoEtablissement(String logoEtablissement) {
		this.logoEtablissement = logoEtablissement;
	}

	@JsonProperty("nombrePostes")
	public int getNombrePostes() {
		return nombrePostes;
	}

	@JsonProperty("nombrePostes")
	public void setNombrePostes(int nombrePostes) {
		this.nombrePostes = nombrePostes;
	}

	@JsonProperty("nomCompteEtablissement")
	public String getNomCompteEtablissement() {
		return nomCompteEtablissement;
	}

	@JsonProperty("nomCompteEtablissement")
	public void setNomCompteEtablissement(String nomCompteEtablissement) {
		this.nomCompteEtablissement = nomCompteEtablissement;
	}

	@JsonProperty("nomInterlocuteur")
	public String getNomInterlocuteur() {
		return nomInterlocuteur;
	}

	@JsonProperty("nomInterlocuteur")
	public void setNomInterlocuteur(String nomInterlocuteur) {
		this.nomInterlocuteur = nomInterlocuteur;
	}

	@JsonProperty("numeroOffre")
	public String getNumeroOffre() {
		return numeroOffre;
	}

	@JsonProperty("numeroOffre")
	public void setNumeroOffre(String numeroOffre) {
		this.numeroOffre = numeroOffre;
	}

	@JsonProperty("offreConfidentielle")
	public boolean isOffreConfidentielle() {
		return offreConfidentielle;
	}

	@JsonProperty("offreConfidentielle")
	public void setOffreConfidentielle(boolean offreConfidentielle) {
		this.offreConfidentielle = offreConfidentielle;
	}

	@JsonProperty("offreQualifiee")
	public boolean isOffreQualifiee() {
		return offreQualifiee;
	}

	@JsonProperty("offreQualifiee")
	public void setOffreQualifiee(boolean offreQualifiee) {
		this.offreQualifiee = offreQualifiee;
	}

	@JsonProperty("prenomInterlocuteur")
	public String getPrenomInterlocuteur() {
		return prenomInterlocuteur;
	}

	@JsonProperty("prenomInterlocuteur")
	public void setPrenomInterlocuteur(String prenomInterlocuteur) {
		this.prenomInterlocuteur = prenomInterlocuteur;
	}

	@JsonProperty("salaireTexte")
	public String getSalaireTexte() {
		return salaireTexte;
	}

	@JsonProperty("salaireTexte")
	public void setSalaireTexte(String salaireTexte) {
		this.salaireTexte = salaireTexte;
	}

	@JsonProperty("tempsPartiel")
	public boolean isTempsPartiel() {
		return tempsPartiel;
	}

	@JsonProperty("tempsPartiel")
	public void setTempsPartiel(boolean tempsPartiel) {
		this.tempsPartiel = tempsPartiel;
	}

	@JsonProperty("texteHtml")
	public String getTexteHtml() {
		return texteHtml;
	}

	@JsonProperty("texteHtml")
	public void setTexteHtml(String texteHtml) {
		this.texteHtml = texteHtml;
	}

	@JsonProperty("typeCandidature")
	public String getTypeCandidature() {
		return typeCandidature;
	}

	@JsonProperty("typeCandidature")
	public void setTypeCandidature(String typeCandidature) {
		this.typeCandidature = typeCandidature;
	}

	@JsonProperty("offreSalon")
	public boolean isOffreSalon() {
		return offreSalon;
	}

	@JsonProperty("offreSalon")
	public void setOffreSalon(boolean offreSalon) {
		this.offreSalon = offreSalon;
	}

	@JsonProperty("latitude")
	public double getLatitude() {
		return latitude;
	}

	@JsonProperty("latitude")
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public double getLongitude() {
		return longitude;
	}

	@JsonProperty("longitude")
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@JsonProperty("geolocalisable")
	public boolean isGeolocalisable() {
		return geolocalisable;
	}

	@JsonProperty("geolocalisable")
	public void setGeolocalisable(boolean geolocalisable) {
		this.geolocalisable = geolocalisable;
	}

	@JsonProperty("texteHtmlProfil")
	public String getTexteHtmlProfil() {
		return texteHtmlProfil;
	}

	@JsonProperty("texteHtmlProfil")
	public void setTexteHtmlProfil(String texteHtmlProfil) {
		this.texteHtmlProfil = texteHtmlProfil;
	}

	@JsonProperty("texteHtmlEntreprise")
	public String getTexteHtmlEntreprise() {
		return texteHtmlEntreprise;
	}

	@JsonProperty("texteHtmlEntreprise")
	public void setTexteHtmlEntreprise(String texteHtmlEntreprise) {
		this.texteHtmlEntreprise = texteHtmlEntreprise;
	}

	@JsonProperty("idNomCiviliteSuivi")
	public int getIdNomCiviliteSuivi() {
		return idNomCiviliteSuivi;
	}

	@JsonProperty("idNomCiviliteSuivi")
	public void setIdNomCiviliteSuivi(int idNomCiviliteSuivi) {
		this.idNomCiviliteSuivi = idNomCiviliteSuivi;
	}

	@JsonProperty("enseigne")
	public String getEnseigne() {
		return enseigne;
	}

	@JsonProperty("enseigne")
	public void setEnseigne(String enseigne) {
		this.enseigne = enseigne;
	}

	@JsonProperty("affichageLogo")
	public boolean isAffichageLogo() {
		return affichageLogo;
	}

	@JsonProperty("affichageLogo")
	public void setAffichageLogo(boolean affichageLogo) {
		this.affichageLogo = affichageLogo;
	}

	@JsonProperty("indicateurLm")
	public boolean isIndicateurLm() {
		return indicateurLm;
	}

	@JsonProperty("indicateurLm")
	public void setIndicateurLm(boolean indicateurLm) {
		this.indicateurLm = indicateurLm;
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
