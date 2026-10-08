package App.Universal;

public final class FieldContainer {
    private final String type;
    private final String value;

    public FieldContainer(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FieldContainer{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
