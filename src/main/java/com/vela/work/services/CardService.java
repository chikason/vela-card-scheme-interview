package com.vela.work.services;

import java.util.List;

import com.vela.work.entities.Card;
import com.vela.work.entities.Stats;

public interface CardService {
	
	public Card getCardVerified(String card_no);
	public List<Stats> getCartStats(int start, int limit);
	public void addCard(Card card);
}
