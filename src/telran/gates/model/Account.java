package telran.gates.model;

public class Account {

    private final String name;
    private final int number;
    private double balance;

    public Account(String name, int number, double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double sum){
        balance = getBalance() + sum;
    }

    public boolean decreaseBalance(double sum) {
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
