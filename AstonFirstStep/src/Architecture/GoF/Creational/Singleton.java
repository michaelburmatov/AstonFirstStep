package Architecture.GoF.Creational;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Singleton {

    private static final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public static <T> void register(Class<T> type, T instance) {
        instances.put(type, instance);
    }

    public static <T> T get(Class<T> type) {
        Object instance = instances.get(type);

        if (instance == null) {
            throw new IllegalStateException("No instance registered for " + type.getName());
        }

        return type.cast(instance);
    }
}