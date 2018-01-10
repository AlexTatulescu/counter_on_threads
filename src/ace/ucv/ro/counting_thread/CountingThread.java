package ace.ucv.ro.counting_thread;

import java.util.concurrent.Semaphore;

public class CountingThread extends Thread {

	private Semaphore semaphore;
	private String threadName;
	private Integer limit;

	public CountingThread(Semaphore semaphore, String threadName, Integer limit) {
		this.semaphore = semaphore;
		this.threadName = threadName;
		this.limit = limit;
	}

	private void incrementCounter() {
		try {
			semaphore.acquire();

			System.out.println(threadName + " is allowed to proceed");

			Shared.counter++;

			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
			System.out.println(threadName + " released");
		}
	}

	@Override
	public void run() {
		
		System.out.println("Starting " + threadName);
		
		while (Shared.counter < limit) {
			incrementCounter();
		}
	}
}
