package io.springbootapplication.models;

public class LocationBean {
	
	@Override
	public String toString() {
		return "LocationBean [state=" + state + ", country=" + country + ", totalCases=" + totalCases + "]";
	}
	private int counter;
	private String state;
	private String country;
	private int totalCases;
	private int changeInCases;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getChangeInCases() {
		return changeInCases;
	}
	public void setChangeInCases(int changeInCases) {
		this.changeInCases = changeInCases;
	}
	
}
