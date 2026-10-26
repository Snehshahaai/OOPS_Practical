package service;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import model.*;
import exception.*;
import util.*;

public class BankService {
    private Repository<Account> repository = new Repository<>();
    private Path logFile = Paths.get("data/logs/transactions.log");

    public void load(Path file) {
        try {
            Account[] accounts = StatePersister.load(file);
            repository.saveAll(accounts);
        } catch (Exception e) {
            System.out.println("Could not load accounts: " + e.getMessage());
        }
    }

    public Account openAccount(String owner, long openingBalance, String type)
            throws InvalidAmountException {
        if (openingBalance < 0)
            throw new InvalidAmountException("Opening balance cannot be negative");

        Account account;
        switch (type.toLowerCase()) {
            case "savings" -> account = new SavingsAccount(owner, openingBalance, 500);
            case "current" -> account = new CurrentAccount(owner, openingBalance, 5000);
            case "fixed" -> account = new FixedDepositAccount(owner, openingBalance);
            default -> throw new IllegalArgumentException("Unknown account type");
        }

        repository.save(account);
        return account;
    }

    public Account find(String number) throws AccountNotFoundException {
        Account account = repository.findById(number);
        if (account == null) throw new AccountNotFoundException(number);
        return account;
    }

    public void deposit(String number, long amount)
            throws BankException, IOException {
        Account a = find(number);
        a.deposit(amount);
        TransactionLog.append(logFile, "DEPOSIT " + number + " " + amount);
    }

    public void withdraw(String number, long amount)
            throws BankException, IOException {
        Account a = find(number);
        a.withdraw(amount);
        TransactionLog.append(logFile, "WITHDRAW " + number + " " + amount);
    }

    public void transfer(String from, String to, long amount)
            throws BankException, IOException {
        Account a = find(from);
        Account b = find(to);
        a.transfer(b, amount);
        TransactionLog.append(logFile, "TRANSFER " + from + " " + amount);
    }

    public String statement(String number) throws AccountNotFoundException {
        return StatementFormatter.buildStatement(find(number));
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    public ArrayList<Account> sortedAccounts() {
        ArrayList<Account> list = repository.findAll();
        list.sort(java.util.Comparator.comparingLong(Account::getBalance).reversed());
        return list;
    }

    public void save(Path file) throws IOException {
        StatePersister.save(repository.findAll().toArray(new Account[0]), file);
    }

    public void report() throws IOException {
        ReportGenerator.generate(
                Paths.get("data/logs"),
                Paths.get("data/report.txt"));
    }

    public Repository<Account> getRepository() {
        return repository;
    }
}
