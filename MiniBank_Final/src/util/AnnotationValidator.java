package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.*;

public class AnnotationValidator {
    public static String[] validate(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (field.isAnnotationPresent(Positive.class) && value instanceof Number) {
                    if (((Number) value).longValue() <= 0) {
                        errors.add(field.getAnnotation(Positive.class).message());
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class) && value != null) {
                    int max = field.getAnnotation(MaxLength.class).value();
                    if (value.toString().length() > max) {
                        errors.add(field.getName() + " must have at most " + max + " characters");
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Cannot read " + field.getName());
            }
        }

        return errors.toArray(new String[0]);
    }
}
