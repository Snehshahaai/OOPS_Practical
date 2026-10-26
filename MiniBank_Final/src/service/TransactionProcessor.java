package service;

import java.util.concurrent.*;
import java.util.*;
import model.Account;

public class TransactionProcessor {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String number) {
        return accounts.get(number);
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void submit(Runnable task) {
        pool.execute(task);
    }

    public void stop() {
        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
