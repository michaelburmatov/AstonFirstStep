package App.Universal;

import java.util.Arrays;

public class UniversalClass {
    private final UniversalField<?>[] fields;
    private final int count;
    public UniversalClass(UniversalField<?>[] fields) {
        this.fields = fields;
        count = fields.length;
    }

    public Object getField(int index) {
        return fields[index].getValue();
    }

    public int getCount() {
        return  count;
    }

    @Override
    public String toString() {
        return "UniversalClass{" +
                "fields=" + Arrays.toString(fields) +
                '}';
    }
}
