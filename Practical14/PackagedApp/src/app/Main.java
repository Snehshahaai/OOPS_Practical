package app;

import model.Model;
import service.Service;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        service.add(new Model("Book"));
        service.add(new Model("Pen"));

        System.out.println("Count: " + service.count());
    }
}
