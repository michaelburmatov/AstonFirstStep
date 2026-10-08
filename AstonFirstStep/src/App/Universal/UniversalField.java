package App.Universal;

public class UniversalField<T> {
    private Class<T> type;
    private Object object;

    public UniversalField(Class<T> type, Object object) {
        this.type = type;
        this.object = object;
    }

    public Object getValue() {
        return object;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "UniversalField{" +
                "type=" + type +
                ", object=" + object +
                '}';
    }
}
