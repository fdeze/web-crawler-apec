package fr.fabien.webcrawler.apec.configuration;

public final class ApecConstants {

	/**
	 * APEC code identifier
	 */
	public static final String ACPEC_OFFER_CODE = "APEC_";

	/**
	 * APEC image url
	 */
	public static final String APEC_IMAGE_URL = "https://www.apec.fr/files/live/mounts/images/";
 
	/**
	 * APEC search API input data file name
	 */
	public static final String APEC_SEARCH_API_INPUT_DATA_FILE = "flux_apec.json";
	
	/**
	 * APEC search API Endpoint
	 */
	public static final String SEARCH_OFFER_URL = "https://www.apec.fr/cms/webservices/rechercheOffre";

	/**
	 * APEC offer details API Endpoint
	 */
	public static final String GET_OFFER_DETAILS_URL = "https://www.apec.fr/cms/webservices/offre/public?numeroOffre=";

	/**
	 * Public Internet offer URL
	 */
	public static final String PUBLIC_OFFER_URL = "https://cadres.apec.fr/home/mes-offres/recherche-des-offres-demploi/liste-des-offres-demploi/detail-de-loffre-demploi.html?numIdOffre=";

	public static final double OFFER_PER_PAGE = 20.0;
}
