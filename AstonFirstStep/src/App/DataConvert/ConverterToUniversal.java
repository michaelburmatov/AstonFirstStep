package App.DataConvert;

import App.Debug.Debuger;
import App.Universal.UniversalClass;
import App.Universal.UniversalField;
import Architecture.GoF.Creational.Builder;
import Architecture.GoF.Creational.Singleton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConverterToUniversal<T> extends Builder<List<UniversalClass>, T> {
    private final List<UniversalClass> array = new ArrayList<>();
    private final Class<T> type;

    public ConverterToUniversal(Class<T> type) {
        this.type = type;
    }

    @Override
    public void append(T context) {
        Field[] fields = type.getDeclaredFields();
        var universalFields = new UniversalField<?>[fields.length];

        for (int i = 0; i < fields.length; i++) {
            tryPreparateField(context, fields, i, universalFields);
        }

        array.add(new UniversalClass(universalFields));
    }

    private void tryPreparateField(T context, Field[] fields, int i, UniversalField<?>[] universalFields) {
        var field = fields[i];
        try {
            field.setAccessible(true);
            universalFields[i] = new UniversalField<>(field.getType(), field.get(context));
        } catch (Exception e) {
            Singleton.get(Debuger.class).Log(e);
        }
    }

    @Override
    public List<UniversalClass> build() {
        return array;
    }
}