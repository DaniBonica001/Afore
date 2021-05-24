package model;

public class Reserve {

	//Attributes
	private int time;
	private boolean cold;
	private boolean covid_19;
	private int amountPeople;
	
	//Relations 
	private Reserve next;
	private Reserve previous;
	
	//Constructor
	public Reserve(int time, boolean cold, boolean covid_19, int people) {
		this.time = time;
		this.cold = cold;
		this.covid_19 = covid_19;
		amountPeople = people;
	}
	
	//Getters and Setters
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public void setCold(boolean cold) {
		this.cold = cold;
	}

	public boolean isCold() {
		return cold;
	}

	public void setCovid_19(boolean covid_19) {
		this.covid_19 = covid_19;
	}

	public boolean isCovid_19() {
		return covid_19;
	}

	public void setAmountPeople(int amountPeople) {
		this.amountPeople = amountPeople;
	}

	public int getAmountPeople() {
		return amountPeople;
	}

	public void setNext(Reserve next) {
		this.next = next;
	}

	public Reserve getNext() {
		return next;
	}

	public void setPrevious(Reserve previous) {
		this.previous = previous;
	}

	public Reserve getPrevious() {
		return previous;
	}

	//***********************************************************************************************************************************
	
	
}
