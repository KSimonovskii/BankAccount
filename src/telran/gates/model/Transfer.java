package telran.gates.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Transfer implements Runnable {

    private static final Lock mutex = new ReentrantLock();
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

        Account firstLock = accountFrom.getNumber() > accountTo.getNumber() ? accountTo : accountFrom;
        Account secondLock = accountFrom.getNumber() > accountTo.getNumber() ? accountFrom : accountTo;

        firstLock.lock();
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!firstLock.decreaseBalance(amount)) {
                return;
            }
            secondLock.lock();
            try {
                secondLock.addBalance(amount);
            } finally {
                secondLock.unlock();
            }
        } finally {
            firstLock.unlock();
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
