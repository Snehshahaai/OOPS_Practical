package app;

import model.Account;
import model.SavingsAccount;
import service.TransactionProcessor;

public class ConcurrencyDemo {
    public static void main(String[] args) {
        Account account = new SavingsAccount("Thread User", 0, 0);

        TransactionProcessor processor = new TransactionProcessor();
        processor.addAccount(account);

        for (int i = 0; i < 10; i++) {
            processor.submit(() -> {
                try {
                    for (int j = 0; j < 1000; j++)
                        account.deposit(1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }

        processor.stop();
        System.out.println("Final balance: " + account.getBalance());
    }
}
