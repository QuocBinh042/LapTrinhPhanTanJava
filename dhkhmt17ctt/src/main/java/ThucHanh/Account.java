package ThucHanh;

public class Account {
	
	private String name;
	private double balance;
	
	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	public Account() {
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public synchronized void deposit (double amount) {
		
		if(amount <= 0)
			throw new IllegalArgumentException("Amount must be > 0!");
		
		balance += amount;
	}
	
	public  double withdraw (double amount) {
		if(amount <= 0)
			throw new IllegalArgumentException("Amount must be > 0!");
		
		if(amount > balance) {
			throw new IllegalArgumentException("Amount must be < balance!");
		}
		
		balance -= amount;
		
		return amount;
	}

}

// Checked Exception
// Unchecked Exception


//Exception
//RuntimeException -->  Unchecked Exception