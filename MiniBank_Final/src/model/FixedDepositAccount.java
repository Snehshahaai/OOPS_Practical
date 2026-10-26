package model;

public class FixedDepositAccount extends Account implements InterestBearing {
    private static final long serialVersionUID = 1L;

    public FixedDepositAccount(String ownerName, long openingBalance) {
        super(ownerName, openingBalance);
    }

    @Override
    public double interestRate() { return 7.0; }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }

    @Override
    public double getBalanceForInterest() {
        return getBalance();
    }
}
