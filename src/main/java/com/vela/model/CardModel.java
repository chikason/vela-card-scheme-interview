package com.vela.model;

import com.vela.work.entities.Card;

public class CardModel {

	private boolean success;
	private Card payload;
	
	public CardModel() {
	}
	

	public CardModel(boolean success, Card payload) {
		this.success = success;
		this.payload = payload;
	}



	public boolean isSuccess() {
		return success;
	}
	

	public void setSuccess(boolean success) {
		this.success = success;
	}
	

	public Card getPayload() {
		return payload;
	}
	

	public void setPayload(Card payload) {
		this.payload = payload;
	}
	
	
	
}
