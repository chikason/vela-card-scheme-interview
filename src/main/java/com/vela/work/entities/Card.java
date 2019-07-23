package com.vela.work.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String cardNo;
	private String scheme;
	private String type;
	private String bank;
	
	
	public Card() {
	}

	public Card(long id, String cardNo, String scheme, String type, String bank) {
		super();
		this.id = id;
		this.cardNo = cardNo;
		this.scheme = scheme;
		this.type = type;
		this.bank = bank;
	}
	

	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}
	

	public String getCardNo() {
		return cardNo;
	}
	

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	

	public String getScheme() {
		return scheme;
	}
	

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

	public String getBank() {
		return bank;
	}
	

	public void setBank(String bank) {
		this.bank = bank;
	}

	
}
