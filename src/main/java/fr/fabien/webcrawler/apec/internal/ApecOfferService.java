package fr.fabien.webcrawler.apec.internal;

import java.util.List;

import fr.fabien.webcrawler.common.model.ApecOfferVo;

public interface ApecOfferService {

	public List<ApecOfferVo> execute(String keyword);
}
