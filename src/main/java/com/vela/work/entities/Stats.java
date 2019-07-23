package com.vela.work.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stats {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private String cardNo;
	private int records;
	
	public Stats() {
	}

	public Stats(long id, String cardNo, int records) {
		super();
		this.id = id;
		this.cardNo = cardNo;
		this.records = records;
	}

	public String getId() {
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

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}
	
	
	
	
	

}
