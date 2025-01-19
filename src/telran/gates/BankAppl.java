package telran.gates;

import telran.gates.model.Account;
import telran.gates.model.Transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BankAppl {

    private static final double MIN_AMOUNT = 300d;
    private static final double MAX_AMOUNT = 1000d;
    public static void main(String[] args) throws IOException, InterruptedException {

        Account account1 = new Account("Account A", 1000, 100_000);
        Account account2 = new Account("Account B", 2000, 200_000);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter count of transactions or 0 for exit:");
        int countIteration = Integer.parseInt(bf.readLine());

        Thread[] transfers = new Thread[countIteration];

        for (int i = 0; i < transfers.length; i++) {

            Account accountFrom = new Random().nextInt(0, 2) == 0? account1: account2;
            Account accountTo = accountFrom == account1? account2 : account1;

            Transfer transfer = new Transfer(accountFrom, accountTo, new Random().nextDouble(MIN_AMOUNT, MAX_AMOUNT));

            transfers[i] = new Thread(transfer);
            transfers[i].start();
        }

        for (int i = 0; i < transfers.length; i++) {
            transfers[i].join();
        }

        System.out.println("Transers is finished");
        System.out.println("Balance of account " + account1.getName() + " is " + account1.getBalance());
        System.out.println("Balance of account " + account2.getName() + " is " + account2.getBalance());

    }


}
