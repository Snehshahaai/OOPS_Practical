package app;

import java.nio.file.*;
import java.util.Scanner;
import model.*;
import exception.*;
import service.*;
import util.*;
import static util.Validator.isValidMobile;

public class MiniBank {
    private static final Path DATA_FILE = Paths.get("data/accounts.dat");

    public static void main(String[] args) {
        // Static import example: use a Validator method without writing Validator.
        boolean demoValidation = isValidMobile("9876543210");
        BankService bank = new BankService();
        bank.load(DATA_FILE);

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       MINI BANK - CHARUSAT     ");
        System.out.println("================================");

        while (true) {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Report");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            String input = sc.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        System.out.print("Owner name: ");
                        String name = sc.nextLine();

                        System.out.print("Type (savings/current/fixed): ");
                        String type = sc.nextLine();

                        System.out.print("Opening balance: ");
                        long amount = Long.parseLong(sc.nextLine());

                        Account a = bank.openAccount(name, amount, type);
                        System.out.println("Account opened: " + a.getAccountNumber());
                    }

                    case 2 -> {
                        System.out.print("Account number: ");
                        String number = sc.nextLine();
                        System.out.print("Amount: ");
                        long amount = Long.parseLong(sc.nextLine());

                        bank.deposit(number, amount);
                        System.out.println("Deposit successful.");
                    }

                    case 3 -> {
                        System.out.print("Account number: ");
                        String number = sc.nextLine();
                        System.out.print("Amount: ");
                        long amount = Long.parseLong(sc.nextLine());

                        bank.withdraw(number, amount);
                        System.out.println("Withdrawal successful.");
                    }

                    case 4 -> {
                        System.out.print("From account: ");
                        String from = sc.nextLine();
                        System.out.print("To account: ");
                        String to = sc.nextLine();
                        System.out.print("Amount: ");
                        long amount = Long.parseLong(sc.nextLine());

                        bank.transfer(from, to, amount);
                        System.out.println("Transfer successful.");
                    }

                    case 5 -> {
                        System.out.print("Account number: ");
                        System.out.println(bank.statement(sc.nextLine()));
                    }

                    case 6 -> {
                        for (Account a : bank.sortedAccounts())
                            System.out.println(a);
                    }

                    case 7 -> bank.report();

                    case 8 -> {
                        bank.save(DATA_FILE);
                        System.out.println("Data saved. Goodbye!");
                        return;
                    }

                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InsufficientFundsException e) {
                System.out.println("Withdrawal failed: short by " + e.getShortfall());
            } catch (BankException | java.io.IOException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
}
