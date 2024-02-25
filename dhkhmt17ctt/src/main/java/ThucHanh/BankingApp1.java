package ThucHanh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankingApp1 {

	private static Account account = new Account("Lan", 0);
	
	public static void main(String[] args) {
		
		Runnable task = () -> {
			account.deposit(100.0);
		};
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 100; i++) {
			service.submit(task);
		}
		
		service.shutdown();
		
		while(!service.isTerminated()) {
			//wait all tasks to finish
		}
		
		System.out.println("Balance: " + account.getBalance()); // Expected Balance: 10_000.0
	}
}
