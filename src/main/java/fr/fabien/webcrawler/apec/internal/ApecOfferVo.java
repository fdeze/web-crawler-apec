package fr.fabien.webcrawler.apec.internal;

import fr.fabien.webcrawler.common.model.AbstractOfferVo;

public class ApecOfferVo extends AbstractOfferVo {

	private String entreprise;

	private String salaire;

	private String numeroOffre;

	private String adresseSiege;

	private String adresse;

	private String texteHtml;

	private String texteEntreprise;

	private String texteProfil;

	private String urlLogo;

	public String getAdresse() {
		return adresse;
	}

	public String getAdresseSiege() {
		return adresseSiege;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public String getNumeroOffre() {
		return numeroOffre;
	}

	public String getSalaire() {
		return salaire;
	}

	public String getTexteEntreprise() {
		return texteEntreprise;
	}

	public String getTexteHtml() {
		return texteHtml;
	}

	public String getTexteProfil() {
		return texteProfil;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setAdresseSiege(String adresseSiege) {
		this.adresseSiege = adresseSiege;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public void setNumeroOffre(String numeroOffre) {
		this.numeroOffre = numeroOffre;
	}

	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}

	public void setTexteEntreprise(String texteEntreprise) {
		this.texteEntreprise = texteEntreprise;
	}

	public void setTexteHtml(String texteHtml) {
		this.texteHtml = texteHtml;
	}

	public void setTexteProfil(String texteProfil) {
		this.texteProfil = texteProfil;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	@Override
	public String toString() {
		return "ApecOfferVo [entreprise=" + entreprise + ", salaire=" + salaire + ", numeroOffre=" + numeroOffre
				+ ", adresseSiege=" + adresseSiege + ", adresse=" + adresse + ", texteHtml=" + texteHtml
				+ ", texteEntreprise=" + texteEntreprise + ", texteProfil=" + texteProfil + "]";
	}

}
