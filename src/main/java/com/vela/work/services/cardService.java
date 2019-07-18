package com.vela.work.services;

import java.util.List;

import com.vela.work.entities.Card;
import com.vela.work.entities.Stats;

public interface cardService {
	
	public Card getCardVerified(Long card_no);
	public List<Stats> getCartStats(int start, int limit);
}
