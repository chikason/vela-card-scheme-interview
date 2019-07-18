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
public class cardServiceImp implements cardService{
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	StatRepository statRepo;


	@Override
	public Card getCardVerified(Long card_no) {
		// TODO Auto-generated method stub
		return cardRepo.findByCardNo(card_no);
	}

	@Override
	public List<Stats> getCartStats(int start, int limit) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(start, limit);
		List<Stats> stats = new ArrayList<>();
		try {
		Page<Stats>  pg = statRepo.findAll(page);
		stats = pg.getContent();
		
		}
		catch(Exception e) { 
			e.printStackTrace();
			System.out.println(e.getMessage());
			}
		
		return stats;
	}

}
