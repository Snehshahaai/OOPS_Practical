/*
JUnit 5 test example.

Add JUnit 5 to the project, then run this file with the JUnit test runner.
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LayeredAppTest {
    @Test
    void addingProductIncreasesCount() {
        // In a real project, ProductService would be public or in its own file.
        ProductService service = new ProductService();
        int oldCount = service.count();

        service.add(new Product("Book"));

        assertEquals(oldCount + 1, service.count());
    }

    @Test
    void invalidExampleThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Invalid action");
        });
    }
}
