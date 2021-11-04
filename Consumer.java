import java.util.ArrayList;

public class Consumer implements Runnable{
	ArrayList <Integer> buffer;

	/*Constructor*/
	public Consumer(ArrayList<Integer> buffer){
		this.buffer = buffer;
	}

	/*Method that removes an object from shared resource*/
	public void removeObject() throws InterruptedException{
		synchronized(buffer){
			while(buffer.isEmpty()){
				System.out.println("\nBuffer is empty. Cannot remove any objects.");
				buffer.wait();
			}
			buffer.remove(0);
			System.out.println("\nRemoved 1 object from the buffer.");
			System.out.println("Buffer currently contains " + buffer.size() + " objects.");
			Thread.sleep(1000); //delayed longer than in Producer thread class 
								//to make it easier to get cases where buffer is full
			buffer.notify();
		}
	}

	@Override
	public void run(){
		while (true){
			try{
				removeObject();
			}
			catch(InterruptedException ie){};
		}
	}

}