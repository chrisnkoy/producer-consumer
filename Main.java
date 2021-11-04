import java.util.ArrayList;

public class Main{
	public static void main(String [] args){
		//System.out.println("Hello from Main");
		ArrayList<Integer> buffer = new ArrayList<Integer>();//shared resource
		Producer producer = new Producer(buffer); //producer thread
		Consumer consumer = new Consumer(buffer); //consumer thread

		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);

		t1.start();
		t2.start();

		/*  join all thread to the main thread to make
			main thread wait until all threads are done executing
			which in this program will be...never, unless forced 
			to by user using "Control C" for example                */
		try{
			t1.join();
			t2.join();
		}

		catch(InterruptedException ie){}

	}
}