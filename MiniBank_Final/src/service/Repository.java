package service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.annotation.Id;

public class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public T save(T entity) {
        data.put(getId(entity), entity);
        return entity;
    }

    public T findById(String id) {
        return data.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) save(item);
    }

    private String getId(T entity) {
        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return String.valueOf(field.get(entity));
                }
            }
            throw new IllegalArgumentException("No @Id field found");
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Cannot read @Id field", e);
        }
    }
}
