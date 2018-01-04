package ace.ucv.ro.counting_thread;

import java.util.concurrent.Semaphore;

public class CountingThread extends Thread {

	private Semaphore semaphore;
	private String threadName;
	private Integer limit;
	private Integer numberOfIncrements;

	public CountingThread(Semaphore semaphore, String threadName, Integer limit, Integer numberOfIncrements) {
		this.semaphore = semaphore;
		this.threadName = threadName;
		this.limit = limit;
		this.numberOfIncrements = numberOfIncrements;
	}

	@Override
	public void run() {
		if (Shared.counter <= limit) {
			System.out.println("Starting " + threadName);
			try {
				semaphore.acquire();
				System.out.println(threadName + " is allowed to proceed");
				for (int i = 0; i < numberOfIncrements; i++) {
					Shared.counter++;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(threadName + " released");
			semaphore.release();
		}
	}
}
