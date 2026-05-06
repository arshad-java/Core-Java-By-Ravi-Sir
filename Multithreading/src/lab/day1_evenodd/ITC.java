package lab.day1_evenodd;

class Shared
{
	int value;
	boolean hasdata;
	
	public synchronized void produce(int val) throws InterruptedException
	{
		while(hasdata)
			wait();
		value=val;
		hasdata=true;
		IO.println("Produser"+value);
		notify();
		
	}
	
	public synchronized void consume() throws InterruptedException
	{
		while(!hasdata)
			wait();
		IO.println("consumed"+value);
		hasdata=false;
		
		notify();
		
	}
}

public class ITC {
	
	public static void main(String[] args) {
		
		Shared s = new Shared();
		
		Thread produce = new Thread(()->{
			for(int i=1;i<10;i++) {
				
				try {
					s.produce(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread consumer = new Thread(()->{
			for(int i=1;i<10;i++)
			{
				try {
					s.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		produce.start();
		consumer.start();
		
	}

}
