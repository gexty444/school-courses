package Week6;

public class SynchronizedAccount {
	private volatile int total = 0;
	private final Object writelock = new Object();

	public void deposit(int amount) {
		synchronized (writelock) {
			total += amount;
			System.out.println("Deposite " + amount);
		}
	}

	public void withdraw(int amount) {
		synchronized (writelock) {
			if (total >= amount) {
				total -= amount;
				System.out.println("Withdraw " + amount);
			}
		}
	}

	public int balance() {
		System.out.println("Check balance");
		return total;
	}
}