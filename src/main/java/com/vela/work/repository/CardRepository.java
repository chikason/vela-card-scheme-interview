package com.vela.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vela.work.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	public Card findByCardNo(String cardNo);

}
