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
    private List<UniversalClass> array = new ArrayList<>();
    @Override
    public void append(T context) {
        Field[] fields = context.getClass().getFields(); //.getDeclaredFields();
        var universalFields = new UniversalField<?>[fields.length];
        for (var i = 0; i < fields.length; i++)
            tryPreparateField(context, fields, i, universalFields);

        var universalClass = new UniversalClass(universalFields);
        array.add(universalClass);
    }

    private static <T> void tryPreparateField(T context, Field[] fields, int i, UniversalField<?>[] universalFields) {
        var field = fields[i];
        try {
            universalFields[i] = new UniversalField<>(field.getType(), field.get(context));
        }
        catch (Exception e) {
            Singleton.get(Debuger.class).Log(e);
        }
    }

    @Override
    public List<UniversalClass> build() {
        return array;
    }
}
