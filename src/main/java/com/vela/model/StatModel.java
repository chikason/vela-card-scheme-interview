package com.vela.model;

import java.util.List;

import com.vela.work.entities.Stats;

public class StatModel {
	
	private boolean success;
	private int start;
	private int limit;
	private int size;
	private List<Stats> payload;
	
	
	public StatModel() {
	}
	
	
	public StatModel(boolean success, int start, int limit, int size, List<Stats> payload) {
		super();
		this.success = success;
		this.start = start;
		this.limit = limit;
		this.size = size;
		this.payload = payload;
	}




	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public List<Stats> getPayload() {
		return payload;
	}


	public void setPayload(List<Stats> payload) {
		this.payload = payload;
	}
	
	
	
	
}
