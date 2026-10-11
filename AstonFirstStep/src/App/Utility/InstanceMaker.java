package App.Utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class InstanceMaker {
    public static Object createInstance(Class<?> type, List<String> values) {
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

    public static Object createInstanceFromBytes(Class<?> type, List<byte[]> values) {
        try {
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();

            Field[] classFields = type.getDeclaredFields();

            for (int i = 0; i < Math.min(values.size(), classFields.length); i++) {
                Field field = classFields[i];
                byte[] value = values.get(i);

                field.setAccessible(true);
                Object convertedValue = convertValue(value, field.getType());
                field.set(instance, convertedValue);
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании экземпляра класса " + type.getName(), e);
        }
    }

    private static Object convertValue(byte[] value, Class<?> type) {
        if (value == null || value.length == 0) {
            return getDefaultValue(type);
        }

        try {
            ByteBuffer buffer = ByteBuffer.wrap(value);

            if (type == byte[].class) {
                return value;
            } else if (type == String.class) {
                return new String(value, StandardCharsets.UTF_8);
            } else if (type == int.class || type == Integer.class) {
                return buffer.getInt();
            } else if (type == long.class || type == Long.class) {
                return buffer.getLong();
            } else if (type == double.class || type == Double.class) {
                return buffer.getDouble();
            } else if (type == float.class || type == Float.class) {
                return buffer.getFloat();
            } else if (type == boolean.class || type == Boolean.class) {
                return value[0] != 0;
            } else if (type == short.class || type == Short.class) {
                return buffer.getShort();
            } else if (type == byte.class || type == Byte.class) {
                return value[0];
            } else if (type == char.class || type == Character.class) {
                return buffer.getChar();
            }

            throw new IllegalArgumentException("Неподдерживаемый тип: " + type.getName());
        } catch (Exception e) {
            throw new RuntimeException("Не удалось преобразовать byte[] в тип " + type.getName(), e);
        }
    }

    private static Object convertValue(String value, Class<?> type) {
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

    private static Object getDefaultValue(Class<?> type) {
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
