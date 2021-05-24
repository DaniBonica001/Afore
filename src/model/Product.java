package model;

public class Product {
	
	//Atributes
	private String id;
	private String name;
	private String category;
	private String size;
	private String price;
	private int availability;
	private String description;
	
	//Relations
	private Condition condition;
	
	//Constructor #1
	public Product (String id,String name,String category,String size, String price, int availability,String description) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.size = size;
		this.price = price;
		this.availability = availability;
		this.description = description;
		condition = Condition.ACTIVE;
	}
	
	//Getters and Setters
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getAvailability() {
		return availability;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	//*********************************************************************************************************

	
	
	

}
