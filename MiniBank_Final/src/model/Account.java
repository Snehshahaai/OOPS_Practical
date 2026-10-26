package model;

import java.io.Serializable;
import java.util.Objects;
import model.annotation.Id;
import model.annotation.Positive;
import exception.*;

public abstract class Account implements Transactable, Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;
    private static long counter = 1;

    @Id
    private final String accountNumber;

    private String ownerName;

    @Positive
    private long balance;

    private boolean active = true;

    protected Account(String ownerName, long openingBalance) {
        this.accountNumber = String.format("AC%04d", counter++);
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException("Deposit amount must be greater than 0");
        balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException("Withdrawal amount must be greater than 0");

        if (!canWithdraw(amount)) {
            long allowed = balance;
            if (this instanceof CurrentAccount) {
                allowed += ((CurrentAccount) this).getOverdraftLimit();
            }
            long shortfall = Math.max(1, amount - allowed);
            throw new InsufficientFundsException(shortfall);
        }

        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws BankException {
        if (to == null) throw new BankException("Target account is missing");
        if (to == this) throw new BankException("Cannot transfer to the same account");

        Account first = this.accountNumber.compareTo(to.accountNumber) < 0 ? this : to;
        Account second = first == this ? to : this;

        synchronized (first) {
            synchronized (second) {
                withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;
        Account other = (Account) obj;
        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
