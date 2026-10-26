package model;

public class CurrentAccount extends Account implements InterestBearing {
    private static final long serialVersionUID = 1L;
    private long overdraftLimit;

    public CurrentAccount(String ownerName, long openingBalance, long overdraftLimit) {
        super(ownerName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    public long getOverdraftLimit() { return overdraftLimit; }

    @Override
    public double interestRate() { return 0.0; }

    @Override
    public boolean canWithdraw(long amount) {
        return getBalance() - amount >= -overdraftLimit;
    }

    @Override
    public double getBalanceForInterest() {
        return getBalance();
    }
}
