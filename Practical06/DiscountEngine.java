import java.util.*;
import java.util.function.Function;

public class DiscountEngine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> prices = Arrays.asList(100.0, 200.0, 500.0);

        System.out.println("1. 10% discount");
        System.out.println("2. 20% discount");
        System.out.print("Choose: ");
        int choice = sc.nextInt();

        Function<Double, Double> rule;

        if (choice == 1)
            rule = p -> p * 0.90;
        else
            rule = p -> p * 0.80;

        for (double price : prices) {
            System.out.println(price + " -> " + rule.apply(price));
        }
    }
}
