package App.States.Strategizing;

import App.AppDataStorage;
import Architecture.GoF.Behavioral.StratageResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HandInputStratage implements StratageResult<List<?>, Scanner> {

    private final AppDataStorage storage;

    public HandInputStratage(AppDataStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<?> doStratage(Scanner context) {

        System.out.println("ФОРМАТ ВВОДА <String, Integer, String; String, Integer, String; ...> или 'exit':");
        String input = context.nextLine();

        if ("exit".equalsIgnoreCase(input.trim())) return null;
        if (input.isBlank()) return new ArrayList<>();

        Class<?> type = storage.getType();
        List<Object> result = new ArrayList<>();

        List<String> rows = Arrays.stream(input.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        for (String row : rows) {
            Object obj = buildObject(type, row);
            if (obj != null) result.add(obj);
        }
        return result;
    }

    private Object buildObject(Class<?> type, String row) {
        try {
            var ctor = type.getDeclaredConstructor();
            ctor.setAccessible(true);
            Object instance = ctor.newInstance();

            String[] parts = row.split(",");
            Field[] fields = type.getDeclaredFields();

            for (int i = 0; i < Math.min(parts.length, fields.length); i++) {
                Field f = fields[i];
                f.setAccessible(true);
                f.set(instance, convert(parts[i].trim(), f.getType()));
            }
            return instance;
        } catch (Exception e) {
            System.out.println("Не удалось собрать объект: " + row);
            return null;
        }
    }

    private Object convert(String value, Class<?> type) {
        if (type == int.class || type == Integer.class)   return Integer.parseInt(value);
        if (type == long.class || type == Long.class)     return Long.parseLong(value);
        if (type == double.class || type == Double.class) return Double.parseDouble(value);
        if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
        return value;
    }
}