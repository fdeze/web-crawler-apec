package fr.fabien.webcrawler.apec.internal;

import java.util.List;

import fr.fabien.contracts.apec.ApecOfferVo;

public interface ApecOfferService {

	public List<ApecOfferVo> getOffers(String keyword);
}
