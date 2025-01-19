package telran.gates.model;

public class Transfer implements Runnable {

    private Account accountFrom;
    private Account accountTo;
    private double amount;

    public Transfer(Account accountFrom, Account accountTo, double amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (accountFrom){
            accountFrom.decreaseBalance(amount);
            synchronized (accountTo){
                accountTo.addBalance(amount);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String toString() {
        return "Transfer{" +
                "accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
