package App.States.Strategizing;

import App.States.Strategizing.Contexts.FileInputContext;
import App.Utility.InstanceMaker;
import Architecture.GoF.Behavioral.StratageResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileInputStratage implements StratageResult<List<?>, FileInputContext> {

    @Override
    public List<?> doStratage(FileInputContext context) {
        Class<?> targetClass = context.getType();
        String path = context.getPath();
        String filePath = (path != null && !path.trim().isEmpty()) ? path : "FileInputData.txt";

        List<Object> result = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            int code;
            boolean readingField = false;

            StringBuilder buffer = new StringBuilder();
            List<String> fieldValues = new ArrayList<>();

            while ((code = reader.read()) != -1) {
                char symbol = (char) code;

                if (symbol == '@') {
                    if (readingField && buffer.length() > 0) {
                        fieldValues.add(buffer.toString().trim());
                        buffer.setLength(0);
                    }

                    if (!fieldValues.isEmpty()) {
                        Object instance = InstanceMaker.createInstance(targetClass, fieldValues);
                        result.add(instance);
                    }

                    fieldValues = new ArrayList<>();
                    readingField = false;
                    continue;
                }

                if (symbol == '!') {
                    if (buffer.length() > 0) {
                        fieldValues.add(buffer.toString().trim());
                        buffer.setLength(0);
                    }
                    readingField = true;
                    continue;
                }

                buffer.append(symbol);
            }

            if (buffer.length() > 0) {
                fieldValues.add(buffer.toString().trim());
            }
            if (!fieldValues.isEmpty()) {
                Object instance = InstanceMaker.createInstance(targetClass, fieldValues);
                result.add(instance);
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + filePath, e);
        }

        return result;
    }


}