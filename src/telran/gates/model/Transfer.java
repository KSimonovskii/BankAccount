package telran.gates.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Transfer implements Runnable {

    private static Lock mutex = new ReentrantLock()
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

        accountFrom.lock();
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!accountFrom.decreaseBalance(amount)) {
                return;
            }
            accountTo.lock();
            try {
                accountTo.addBalance(amount);
            } finally {
                accountTo.unlock();
            }
        } finally {
            accountFrom.unlock();
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
