package App.States.Strategizing;

import App.AppDataStorage;
import App.States.Strategizing.Sorting.BubbleSortStratage;
import App.States.Strategizing.Sorting.InsertionSortStratage;
import App.States.Strategizing.Sorting.QuickSortStratage;
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

        System.out.println("Ввод (формат: String, Integer, String; String, Integer, String; ...) или 'exit':");
        String input = context.nextLine();

        if ("exit".equalsIgnoreCase(input.trim())) return null;

        System.out.print("Поле для сортировки (0/1/2): ");
        storage.setSortFieldIndex(parseIntSafe(context.nextLine(), 0));

        System.out.print("Направление (1 - возрастание, 2 - убывание): ");
        storage.setSortDescending("2".equals(context.nextLine().trim()));

        System.out.print("Алгоритм (1 - пузырёк, 2 - быстрая, 3 - вставками): ");
        storage.setSortAlgorithm(pickAlgorithm(context.nextLine().trim()));

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

    private int parseIntSafe(String s, int fallback) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return fallback; }
    }

    private Class<?> pickAlgorithm(String choice) {
        switch (choice) {
            case "1": return BubbleSortStratage.class;
            case "3": return InsertionSortStratage.class;
            case "2":
            default:  return QuickSortStratage.class;
        }
    }
}