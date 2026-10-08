package App.Universal;

import java.util.Arrays;

public class ClassContainer {
    private final String nameClass;
    private final FieldContainer[] fields;
    private final int count;

    public ClassContainer(String nameClass, FieldContainer[] fields) {
        this.nameClass = nameClass;
        this.fields = fields;
        count = fields.length;
    }

    public Object getField(int index) {
        return fields[index].getValue();
    }

    public int getCount() {
        return  count;
    }

    public String getNameClass() {
        return nameClass;
    }

    @Override
    public String toString() {
        return "ClassContainer{" +
                "nameClass='" + nameClass + '\'' +
                ", fields=" + Arrays.toString(fields) +
                ", count=" + count +
                '}';
    }
}
