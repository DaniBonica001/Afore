package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import exceptions.NoNumericInputException;

class RestaurantTest {
	private Restaurant restaurant;
	
	//ESCENARIOS
	public void ScenaryProductEmptyList() {
		restaurant= new Restaurant();
	}
	public void ScenaryProductNotEmptyList() {
		restaurant= new Restaurant();
		restaurant.getProducts().add(new Product("10277", "Pasta Alfredo", "Pastas", "Normal", "40000", 1000, ""));
	}
	public void ScenaryClientEmptyLinkedList() {
		restaurant= new Restaurant();
	}
	public void ScenaryClientNotEmptyLinkedList() {
		restaurant= new Restaurant();
		restaurant.setFirstClient(new Client("Pepe","2345","av6n","31546231","None"));
	}
	
	//METODOS DE PRUEBA
	@Test
	public void addProductTest1() throws NoNumericInputException {//AÑADIR PRODUCTO CUANDO NO HAY PRODUCTOS
		ScenaryProductEmptyList();
		
		String id="1098";
		String name="Baby Beef";
		String cat="Carne";
		String size="Normal";
		String price="22000";
		int avail=50;
		String desc="";
		
		restaurant.addProduct(id, name, cat, size, price, avail, desc);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(1, products.size());

	}
	
	@Test
	public void addProductTest2() throws NoNumericInputException {
		ScenaryProductNotEmptyList();
		
		String id="1099";
		String name="Pizza Peperonni";
		String cat="Pizzas";
		String size="Grande";
		String price="32000";
		int avail=50;
		String desc="";
		
		restaurant.addProduct(id, name, cat, size, price, avail, desc);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(2, products.size());
	}
	
	@Test
	public void addProductTest3() throws NoNumericInputException {
		ScenaryProductNotEmptyList();
		
		String id="10277";
		String name="Pasta Alfredo";
		String cat="Pasta";
		String size="Normal";
		String price="40000";
		int avail=100;
		String desc="";
		
		restaurant.addProduct(id, name, cat, size, price, avail, desc);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(1, products.size());
	}
	
	@Test
	public void deleteproduct1(){
		ScenaryProductEmptyList();
		
		String id="1098";
		
		restaurant.deleteProduct(id);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(0, products.size());
	}
	@Test
	public void deleteproduct2() {
		ScenaryProductNotEmptyList();
		
		String id="10277";
		
		restaurant.deleteProduct(id);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(0, products.size());
	}
	
	@Test
	public void updateProduct1() {
		ScenaryProductEmptyList();
		
		
		String id="10277";
		String name="Pasta Alfredo";
		String cat="Pasta";
		String size="Normal";
		String price="40000";
		int avail=100;
		
		
		restaurant.updateProduct(id, name, cat, size,  price, avail);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(0, products.size());
	}
	
	@Test
	public void updateProduct2() {
		ScenaryProductNotEmptyList();
		
		String id="10277";
		String name="Pasta Artesanal";
		String cat="Pasta";
		String size="Normal";
		String price="20000";
		int avail=100;
		
		
		restaurant.updateProduct(id, name, cat, size,  price, avail);
		
		List<Product> products= restaurant.getProducts();
		
		assertEquals(1, products.size());
		assertEquals("Pasta Artesanal", restaurant.getProducts().get(0).getName());
		assertEquals("20000", restaurant.getProducts().get(0).getPrice());
	}
	
	@Test
	public void addClientTest1(){//AÑADIR CLIENTE CUANDO NO FIRSTCLIENT=NULL
		ScenaryClientEmptyLinkedList();

		String id="4550";
		String name="Pedro";
		String adress="av7n";
		String phone="3122870";
		String obs="Es Hombre";

		restaurant.addClient(id, name, adress, phone, obs);
		
		Client clientAdded= new Client(id, name, adress, phone, obs);
		
		assertEquals(clientAdded.getId(), restaurant.getFirstClient().getId());

	}
	
	@Test
	public void addClientTest2(){//AÑADIR CLIENTE CUANDO YA HAY FIRSTCIENT
		ScenaryClientNotEmptyLinkedList();

		String id="4550";
		String name="Pedro";
		String adress="av7n";
		String phone="3122870";
		String obs="Es Hombre";

		restaurant.addClient(id, name, adress, phone, obs);
		
		Client clientAdded= new Client(id, name, adress, phone, obs);
		
		assertEquals(clientAdded.getId(), restaurant.getFirstClient().getNext().getId());

	}
	
	@Test
	public void addClientTest3(){//AÑADIR CLIENTE REPETIDO
		ScenaryClientNotEmptyLinkedList();

		String id="2345";
		String name="Pedro";
		String adress="av7n";
		String phone="3122870";
		String obs="Es Hombre";

		restaurant.addClient(name, id, adress, phone, obs);
		
		assertEquals(null, restaurant.getFirstClient().getNext());

	}
	@Test
	public void deleteClientTest1(){//ELIMINAR CLIENTE CUANDO FIRSTCLIENT ES NULL
		ScenaryClientEmptyLinkedList();

		String id="1098";

		restaurant.deleteClient(id);

		
		assertEquals(null, restaurant.getFirstClient());

	}
	@Test
	public void deleteClientTest2(){//ELIMINAR CLIENTE FIRSTCLIENT
		ScenaryClientNotEmptyLinkedList();
		

		String id="2345";
		
		assertEquals("2345", restaurant.getFirstClient().getId());

		restaurant.deleteClient(id);

		
		assertEquals(null, restaurant.getFirstClient());

	}
	
	@Test
	public void deleteClientTest3(){//ELIMINAR CLIENTE FIRSTCLIENT
		ScenaryClientNotEmptyLinkedList();
		

		String id="459837";
		
		assertEquals("2345", restaurant.getFirstClient().getId());

		restaurant.deleteClient(id);

		
		assertEquals("2345", restaurant.getFirstClient().getId());

	}
	
	@Test
	public void updateClient1() {//ACTUALIZAR CLIENTE CUANDO FIRSTCLIENT ES NULL
		ScenaryClientEmptyLinkedList();
		
		
		String id="4550";
		String name="Pedro";
		String adress="av7n";
		String phone="3122870";
		String obs="Es Hombre";
		
		assertEquals(null, restaurant.getFirstClient());
		
		restaurant.updateClient(id, name, adress, phone, obs);

		
		assertEquals(null, restaurant.getFirstClient());
	}
	
	@Test
	public void updateClient2() {//ACTUALIZAR CLIENTE QUE EXISTE
		ScenaryClientNotEmptyLinkedList();
		
		
		String id="2345";
		String name="Pedro";
		String adress="av7n";
		String phone="3122870";
		String obs="Es Hombre";
		
		assertEquals("Pepe", restaurant.getFirstClient().getName());
		
		restaurant.updateClient(id, name, adress, phone, obs);

		
		assertEquals("Pedro", restaurant.getFirstClient().getName());
	}
	
	@Test
	public void updateClient3() {//ACTUALIZAR CLIENTE QUE NO EXISTE
		ScenaryClientNotEmptyLinkedList();
		
		
		String id="7957868";
		String name="Pedro";
		String adress="av7n";
		String phone="3122870";
		String obs="Es Hombre";
		
		assertEquals("Pepe", restaurant.getFirstClient().getName());
		
		restaurant.updateClient(id, name, adress, phone, obs);

		
		assertEquals("Pepe", restaurant.getFirstClient().getName());
	}
	
	
	
	
	
	
	
	

}
