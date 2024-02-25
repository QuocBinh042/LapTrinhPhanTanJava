package ThucHanh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingApp2 {
	
	private static Account account = new Account("Linh", 10_000.0); 
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<Double> task = () -> {
			synchronized (account) {
				return account.withdraw(100.0);
			}
			
		};
		
		List<Future<Double>> fus = new ArrayList<>();
		
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			Future<Double> fu = service.submit(task);
			fus.add(fu);
		}
		
		double money = 0.0;
		for(Future<Double> fu : fus) {
			money += fu.get();
		}
		
		System.out.println("Money: " + money); // Expected Money: 10_000.0
		System.out.println("Balance: " + account.getBalance()); //Expected Balance: 0.0
		
		service.shutdown();
	}
	
}
