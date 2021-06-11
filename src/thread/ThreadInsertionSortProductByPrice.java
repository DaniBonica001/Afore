package thread;

import model.Restaurant;

public class ThreadInsertionSortProductByPrice extends Thread{
	
	private Restaurant rest;
	
	public ThreadInsertionSortProductByPrice(Restaurant rest) {
		this.rest = rest;				
	}
	
	@Override
	public void run() {		
		//System.out.println("Empieza el hilo");
		synchronized (rest.getProducts()) {
			while(rest.getProducts().size()==0) {	
				//System.out.println("Entro al while del wait");
				try {
					rest.getProducts().wait();
					//System.out.println("Salio del wait");
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}

			while(rest.getProducts().size()!=0) {
				rest.insertionSortProductByPrice(); 
				try {
					//System.out.println("Volvió a dormir");
					rest.getProducts().wait();
					//System.out.println("Volvió a despertar");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
						
		}	
	}
}

