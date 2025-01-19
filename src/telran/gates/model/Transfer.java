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

        Account firstLock = accountFrom.getNumber() > accountTo.getNumber()? accountTo : accountFrom;
        Account secondLock = accountFrom.getNumber() > accountTo.getNumber()? accountFrom : accountTo;
        synchronized (firstLock){

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!accountFrom.decreaseBalance(amount)) {
                return;
            }
            synchronized (secondLock){
                accountTo.addBalance(amount);
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
