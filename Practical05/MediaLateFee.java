abstract class Media {
    String name;
    Media(String name) { this.name = name; }
    abstract double lateFee(int days);
}

class Book extends Media {
    Book(String name) { super(name); }
    double lateFee(int days) { return days * 2; }
}

class DVD extends Media {
    DVD(String name) { super(name); }
    double lateFee(int days) { return days * 5; }
}

class Magazine extends Media {
    Magazine(String name) { super(name); }
    double lateFee(int days) { return days * 3; }
}

public class MediaLateFee {
    public static void main(String[] args) {
        Media[] items = {
            new Book("Java Book"),
            new DVD("Movie"),
            new Magazine("Tech Magazine")
        };

        double total = 0;
        for (Media m : items) {
            double fee = m.lateFee(4);
            System.out.println(m.name + " fee: " + fee);
            total += fee;
        }
        System.out.println("Total fee: " + total);
    }
}
