public class Account {
    private int balance;

    public Account(int initBalance) {
        balance = initBalance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposite(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
