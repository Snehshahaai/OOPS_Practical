package service;

import java.util.ArrayList;
import model.Model;

public class Service {
    private ArrayList<Model> list = new ArrayList<>();

    public void add(Model m) {
        list.add(m);
    }

    public int count() {
        return list.size();
    }
}
