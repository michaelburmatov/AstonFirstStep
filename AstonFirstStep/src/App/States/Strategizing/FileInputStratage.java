package App.States.Strategizing;

import App.States.Strategizing.Contexts.FileInputContext;
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
                        Object instance = createInstance(targetClass, fieldValues);
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
                Object instance = createInstance(targetClass, fieldValues);
                result.add(instance);
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + filePath, e);
        }

        return result;
    }

    private Object createInstance(Class<?> type, List<String> values) {
        try {
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();

            Field[] classFields = type.getDeclaredFields();

            for (int i = 0; i < Math.min(values.size(), classFields.length); i++) {
                Field field = classFields[i];
                String value = values.get(i);

                field.setAccessible(true);
                Object convertedValue = convertValue(value, field.getType());
                field.set(instance, convertedValue);
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании экземпляра класса " + type.getName(), e);
        }
    }

    private Object convertValue(String value, Class<?> type) {
        if (value == null || value.isEmpty()) {
            return getDefaultValue(type);
        }

        try {
            if (type == String.class) {
                return value;
            } else if (type == int.class || type == Integer.class) {
                return Integer.parseInt(value);
            } else if (type == long.class || type == Long.class) {
                return Long.parseLong(value);
            } else if (type == double.class || type == Double.class) {
                return Double.parseDouble(value);
            } else if (type == float.class || type == Float.class) {
                return Float.parseFloat(value);
            } else if (type == boolean.class || type == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (type == short.class || type == Short.class) {
                return Short.parseShort(value);
            } else if (type == byte.class || type == Byte.class) {
                return Byte.parseByte(value);
            } else if (type == char.class || type == Character.class) {
                return value.charAt(0);
            }

            return type.getMethod("valueOf", String.class).invoke(null, value);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось преобразовать значение '" + value + "' в тип " + type.getName(), e);
        }
    }

    private Object getDefaultValue(Class<?> type) {
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == double.class) return 0.0d;
        if (type == float.class) return 0.0f;
        if (type == boolean.class) return false;
        if (type == short.class) return (short) 0;
        if (type == byte.class) return (byte) 0;
        if (type == char.class) return '\u0000';
        return null;
    }
}