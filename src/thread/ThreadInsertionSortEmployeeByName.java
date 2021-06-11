package thread;

import model.Restaurant;

public class ThreadInsertionSortEmployeeByName extends Thread{
	private Restaurant rest;

	public ThreadInsertionSortEmployeeByName(Restaurant rest) {
		this.rest = rest;				
	}

	@Override
	public void run() {		
		//System.out.println("Empieza el hilo");
		synchronized (rest.getEmployees()) {
			while(rest.getEmployees().size()==0) {	
				//System.out.println("Entro al while del wait");
				try {
					rest.getEmployees().wait();
					//System.out.println("Salio del wait");
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}

			while(rest.getEmployees().size()!=0) {
				rest.insertionSortEmployeeByName(); 
				try {
					//System.out.println("Volvió a dormir");
					rest.getEmployees().wait();
					//System.out.println("Volvió a despertar");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}


		}	
	}
}
