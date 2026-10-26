import java.util.ArrayList;

class Product {
    String name;
    Product(String name) { this.name = name; }
}

class ProductService {
    private ArrayList<Product> products = new ArrayList<>();

    void add(Product p) {
        products.add(p);
    }

    int count() {
        return products.size();
    }

    void show() {
        for (Product p : products)
            System.out.println(p.name);
    }
}

public class LayeredApp {
    public static void main(String[] args) {
        ProductService service = new ProductService();

        service.add(new Product("Book"));
        service.add(new Product("Pen"));

        service.show();
        System.out.println("Count: " + service.count());
    }
}
