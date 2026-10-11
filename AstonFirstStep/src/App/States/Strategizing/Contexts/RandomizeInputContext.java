package App.States.Strategizing.Contexts;

public class RandomizeInputContext {
    private final Class<?> type;
    private final int countClasses;
    private final int countFields;
    private final int sizeBufferBytes;

    public RandomizeInputContext(Class<?> type, int count, int countFields, int sizeBufferBytes) {
        this.type = type;
        this.countClasses = count;
        this.countFields = countFields;
        this.sizeBufferBytes = sizeBufferBytes;
    }

    public int getCountClasses() {
        return countClasses;
    }

    public Class<?> getType() {
        return type;
    }

    public int getCountFields() {
        return countFields;
    }

    public int getSizeBufferBytes() {
        return sizeBufferBytes;
    }
}
