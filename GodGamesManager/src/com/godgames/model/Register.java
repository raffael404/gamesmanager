package com.godgames.model;


public class Register {
	private String date;
	private String startTime;
	private String endTime;
	private int tv;
	private boolean paid;
	private float value;
	
	/**
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param tv
	 * @param paid
	 * @param value
	 */
	public Register(String date, String startTime, String endTime, int tv, boolean paid, float value) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tv = tv;
		this.paid = paid;
		this.value = value;
	}
	
	/**
	* @return the date
	*/
	public String getDate() {
		return date;
	}
	/**
	* @return the startTime
	*/
	public String getStartTime() {
		return startTime;
	}
	/**
	* @return the endTime
	*/
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @return the tv
	 */
	public int getTv() {
		return tv;
	}
	/**
	 * @return the paid
	 */
	public boolean isPaid() {
		return paid;
	}
	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}
		
}
