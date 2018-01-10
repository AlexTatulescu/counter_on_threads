package ace.ucv.ro.counting_thread;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class CounterDemo {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Introduceti limita maxima pana la care pot numara thread-urile:");
		int limit = in.nextInt();
		System.out.println("Introduceti numarul de thread-uri dorite:");
		int maxThreads = in.nextInt();

		in.close();

		Semaphore semaphore = new Semaphore(maxThreads);

		Thread[] threads = new Thread[maxThreads];

		for (int i = 0; i < maxThreads; i++) {
			threads[i] = new CountingThread(semaphore, "Thread " + i, limit);
		}

		for (int i = 0; i < maxThreads; i++) {
			threads[i].start();
		}

		for (int i = 0; i < maxThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Counter: " + Shared.counter);
	}

}
