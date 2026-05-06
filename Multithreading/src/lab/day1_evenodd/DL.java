package lab.day1_evenodd;

public class DL {
	
	static Object lock1 = new Object();
	static Object lock2 = new Object();
			
	public static void main(String[] args) {
		
		Thread odd = new Thread() {
			
			@Override
			public void run() {
				
				synchronized (lock1) {
					IO.println("odd lock1");
					synchronized (lock2) {
						IO.println("Odd lock2");
						
					}
					
				}
				
			}
			 
		};
		
		Thread even = new Thread() {
			@Override
			public void run() {
				synchronized (lock2) {
					IO.println("Even lock2");
					synchronized (lock1) {
						IO.println("Even lock1");
						
					}
					
				}
			}
		};
		
		Thread t1 = new Thread(()->{
			synchronized (lock2) {
				IO.println("Even lock2");
				synchronized (lock1) {
					IO.println("Even lock1");
					
				}
				
			}
		}); 
		Thread t2 = new Thread(()->{
			synchronized (lock1) {
				IO.println("Odd lock1");
				synchronized (lock2) {
					IO.println("Odd lock2");
					
				}
				
			}
		}); 
		
		t1.start();
		t2.start();
				
		
		
	}
			

}
