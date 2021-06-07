package model;

public class Day {
	//atributes
	private int day, month, year;
	private int averageServiceGrade;
	private int averageFoodGrade;
	
	//Relations
	private Day right;
	private Day left;
	
	//contructor
	public Day(int day, int month, int year, int averageServiceGrade, int averageFoodGrade) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.averageServiceGrade = averageServiceGrade;
		this.averageFoodGrade = averageFoodGrade;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getAverageServiceGrade() {
		return averageServiceGrade;
	}

	public void setAverageServiceGrade(int averageServiceGrade) {
		this.averageServiceGrade = averageServiceGrade;
	}

	public int getAverageFoodGrade() {
		return averageFoodGrade;
	}

	public void setAverageFoodGrade(int averageFoodGrade) {
		this.averageFoodGrade = averageFoodGrade;
	}

	public Day getRight() {
		return right;
	}

	public void setRight(Day right) {
		this.right = right;
	}

	public Day getLeft() {
		return left;
	}

	public void setLeft(Day left) {
		this.left = left;
	}
	
	
	
	
	
}
