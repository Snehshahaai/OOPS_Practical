package app;

import model.*;
import service.*;
import util.*;

public class FeatureDemo {
    public static void main(String[] args) throws Exception {
        Account savings = new SavingsAccount("Riya", 5000, 500);
        Account current = new CurrentAccount("Amit", 2000, 5000);
        Account fixed = new FixedDepositAccount("Neha", 9000);

        System.out.println("Interest rates:");
        Account[] accounts = {savings, current, fixed};
        for (Account a : accounts)
            System.out.println(a.getAccountNumber() + " -> " + a.interestRate());

        System.out.println("\nWithdrawRule:");
        WithdrawRule rule1 = new WithdrawRule() {
            public boolean allow(Account account, long amount) {
                return account.getBalance() >= amount;
            }
        };
        WithdrawRule rule2 = (account, amount) -> account.getBalance() >= amount;
        System.out.println(rule1.allow(savings, 1000));
        System.out.println(rule2.allow(savings, 1000));

        System.out.println("\nValidators:");
        System.out.println(Validator.isValidMobile("9876543210"));
        System.out.println(Validator.isValidEmail("abc@xyz.com"));

        System.out.println("\nRepository:");
        Repository<Account> repo = new Repository<>();
        repo.saveAll(savings, current, fixed);
        System.out.println(repo.findById(savings.getAccountNumber()));
        System.out.println(repo.findAll().size());

        System.out.println("\nCommand:");
        Command c = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(c);

        System.out.println("\nAnnotation validation:");
        Account bad = new SavingsAccount("Bad", -100, 0);
        for (String error : AnnotationValidator.validate(bad))
            System.out.println(error);
    }
}
