package telran.gates.model;

public class Account {

    private final String name;
    private final String number;
    private double balance;

    public Account(String name, String number, double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void addBalance(double sum){
        balance = getBalance() + sum;
    }

    public synchronized boolean decreaseBalance(double sum) {
        if (getBalance() < sum){
            System.out.println("Insufficient funds in the account " + getName());
            return false;
        }

        balance = getBalance() - sum;
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
