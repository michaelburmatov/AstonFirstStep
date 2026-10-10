package App.States.Strategizing;

import Architecture.GoF.Behavioral.StratageResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HandInputStratage implements StratageResult<List<?>, Scanner> {

    @Override
    public List<?> doStratage(Scanner context) {
        System.out.println("Ввод строки (формат: <<Integer, String, Boolean; Integer, String, Boolean; ...>>)\n");
        String input = context.nextLine();

        if ("exit".equalsIgnoreCase(input.trim())) {
            return null;
        }

        if (input == null || input.isBlank()) {
            return new ArrayList<>();
        }

        return Arrays.stream(input.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}