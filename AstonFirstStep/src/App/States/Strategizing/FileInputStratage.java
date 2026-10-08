package App.States.Strategizing;

import App.Universal.ClassContainer;
import App.Universal.FieldContainer;
import Architecture.GoF.Behavioral.StratageResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileInputStratage implements StratageResult<List<ClassContainer>, String> {

    @Override
    public List<ClassContainer> doStratage(String context) {
        List<ClassContainer> allContainers = new ArrayList<>();

        String filePath = (context != null && !context.trim().isEmpty()) ? context : "FileInputData.txt";

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            int code;

            boolean startReadClassName = false;
            boolean startReadTypeValue = false;
            boolean startReadValue = false;

            StringBuilder buffer = new StringBuilder();
            String nameClass = "";
            String nameType = "";

            List<FieldContainer> fieldContainers = new ArrayList<>();

            while ((code = reader.read()) != -1) {
                char symbol = (char) code;

                if (symbol == '@') {
                    if (startReadValue) {
                        fieldContainers.add(new FieldContainer(nameType, buffer.toString().trim()));
                        buffer.setLength(0);
                    }

                    if (!nameClass.isEmpty() || !fieldContainers.isEmpty()) {
                        allContainers.add(new ClassContainer(nameClass, fieldContainers.toArray(new FieldContainer[0])));
                    }

                    fieldContainers = new ArrayList<>();
                    nameClass = "";
                    nameType = "";

                    startReadClassName = true;
                    startReadTypeValue = false;
                    startReadValue = false;
                    continue;
                }

                if (symbol == '#') {
                    if (startReadClassName) {
                        nameClass = buffer.toString().trim();
                        buffer.setLength(0);
                    }
                    if (startReadValue) {
                        fieldContainers.add(new FieldContainer(nameType, buffer.toString().trim()));
                        buffer.setLength(0);
                    }

                    startReadClassName = false;
                    startReadTypeValue = true;
                    startReadValue = false;
                    continue;
                }

                if (symbol == '!') {
                    if (startReadTypeValue) {
                        nameType = buffer.toString().trim();
                        buffer.setLength(0);
                    }
                    startReadClassName = false;
                    startReadTypeValue = false;
                    startReadValue = true;
                    continue;
                }

                buffer.append(symbol);
            }

            if (startReadValue && buffer.length() > 0) {
                fieldContainers.add(new FieldContainer(nameType, buffer.toString().trim()));
            }
            if (!nameClass.isEmpty() || !fieldContainers.isEmpty()) {
                allContainers.add(new ClassContainer(nameClass, fieldContainers.toArray(new FieldContainer[0])));
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + filePath, e);
        }

        return allContainers;
    }
}