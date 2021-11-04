import java.util.ArrayList;

public class Producer implements Runnable{

	ArrayList <Integer> buffer; //variable for shared resource
	private int objectNo; //keep track of order of added objects
	final int max = 5; //max size of buffer

	/*Constructor*/
	public Producer(ArrayList<Integer> buffer){
		this.buffer = buffer;
		objectNo = 1;
	} 

	/*Method that adds an object to the shared resource*/
	public void addObject(int i) throws InterruptedException{
		//synchronize access to shared object
		synchronized(buffer){
			while(buffer.size() >= max){
				System.out.println("\nObject #" + i +" cannot be added. Buffer is full.");
				buffer.wait(); // sleep while waiting for buffer size to be less than 5
								//and release control over shared resource
			}
			buffer.add(i);
			System.out.println("\nObject #" + i + " was added to Buffer.\nBuffer currently contains "+ buffer.size() +" objects.");
			Thread.sleep(200); //delayed shorter than in the consumer class to make it easier to get
								//cases where buffer is full
			buffer.notify(); //wake up next sleeping thread and release control over shared resource
		}
	}

	//Override run method
	@Override 
	public void run(){
		while(true){
			try{
				addObject(objectNo++);
			}
			catch(InterruptedException ie){}
		}
	}
	
}