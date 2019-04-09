package fr.fabien.webcrawler.apec.internal;

import java.util.List;

public interface ApecOfferService {

	public List<ApecOfferVo> getOffers(String keyword);
}
