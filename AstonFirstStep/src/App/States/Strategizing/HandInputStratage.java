package App.States.Strategizing;

import App.AppDataStorage;
import App.States.Strategizing.Sorting.BubbleSortStratage;
import App.States.Strategizing.Sorting.InsertionSortStratage;
import App.States.Strategizing.Sorting.QuickSortStratage;
import Architecture.GoF.Behavioral.StratageResult;

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

        System.out.println("Ввод строки (формат: <<Integer, String, Boolean; Integer, String, Boolean; ...>>)\n");
        String input = context.nextLine();

        if ("exit".equalsIgnoreCase(input.trim())) {
            return null;
        }

        System.out.print("Поле для сортировки (0/1/2): ");
        int field = parseIntSafe(context.nextLine(), 0);
        storage.setSortFieldIndex(field);

        System.out.print("Направление (1 - возрастание, 2 - убывание): ");
        storage.setSortDescending("2".equals(context.nextLine().trim()));

        System.out.print("Алгоритм (1 - пузырёк, 2 - быстрая, 3 - вставками): ");
        storage.setSortAlgorithm(pickAlgorithm(context.nextLine().trim()));

        if (input.isBlank()) {
            return new ArrayList<>();
        }

        return Arrays.stream(input.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private int parseIntSafe(String s, int fallback) {
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return fallback; }
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