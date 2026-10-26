package util;

import model.*;

public class CommandParser {
    public static Command parse(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3)
            throw new IllegalArgumentException("Use: TYPE ACCOUNT AMOUNT");

        TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
        String account = parts[1];
        long amount = Long.parseLong(parts[2]);

        return new Command(type, account, amount);
    }
}
