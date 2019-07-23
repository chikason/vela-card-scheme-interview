package com.vela.work.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vela.work.entities.Card;
import com.vela.work.entities.Stats;
import com.vela.work.repository.CardRepository;
import com.vela.work.repository.StatRepository;


@Service
public class CardServiceImp implements CardService{
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	StatRepository statRepo;


	@Override
	public Card getCardVerified(String cardNo) {
		// TODO Auto-generated method stub
		return cardRepo.findByCardNo(cardNo);
	}

	@Override
	public List<Stats> getCartStats(int start, int limit) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(start, limit);
		List<Stats> stats = new ArrayList<>();
		
		Page<Stats>  pg = statRepo.findAll(page);
		stats = pg.getContent();
		
		return stats;
	}

	@Override
	public void addCard(Card card) {
		// TODO Auto-generated method stub
		cardRepo.save(card);
	}

}
