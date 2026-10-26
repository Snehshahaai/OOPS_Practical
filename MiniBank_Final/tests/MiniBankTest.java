/*
JUnit 5 example.
Add JUnit 5 to your IDE/project before running this file.
*/
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;
import exception.*;

public class MiniBankTest {
    @Test
    void depositIncreasesBalance() throws Exception {
        Account a = new SavingsAccount("Riya", 5000, 500);
        a.deposit(2000);
        assertEquals(7000, a.getBalance());
    }

    @Test
    void excessiveWithdrawalThrows() {
        Account a = new SavingsAccount("Riya", 1000, 0);

        assertThrows(InsufficientFundsException.class, () -> {
            a.withdraw(5000);
        });
    }

    @Test
    void concurrentDepositsStayCorrect() throws Exception {
        Account a = new SavingsAccount("Riya", 0, 0);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++)
                        a.deposit(1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        assertEquals(10000, a.getBalance());
    }
}
