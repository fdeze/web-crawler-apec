package fr.fabien.webcrawler.apec.internal;

import java.util.List;

import fr.fabien.contracts.apec.ApecOfferVo;

public interface ApecOfferService {

	/**
	 * Get Offers from APEC site
	 * @param location the search location 
	 * @param keyword the search keyword
	 * @return a list of @ApecOfferVo objects
	 */
	public List<ApecOfferVo> getOffers(String location, String keyword);
}
