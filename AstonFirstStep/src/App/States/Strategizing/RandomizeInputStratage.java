package App.States.Strategizing;

import App.InputClasses.Car;
import App.States.Strategizing.Contexts.RandomizeInputContext;
import App.Utility.InstanceMaker;
import Architecture.GoF.Behavioral.StratageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizeInputStratage implements StratageResult<List<?>, RandomizeInputContext> {

    private final Random random = new Random();

    @Override
    public List<?> doStratage(RandomizeInputContext context) {
        List<Object> result = new ArrayList<>();
        var type = context.getType();
        for (int i = 0; i < context.getCountClasses(); i++) {
            List<byte[]> fields = new ArrayList<>();
            for(int y = 0; y < context.getCountFields(); y++) {
                var bytes = new byte[context.getSizeBufferBytes()];
                random.nextBytes(bytes);
                fields.add(bytes);
            }
            var instance = InstanceMaker.createInstanceFromBytes(type, fields);
            result.add(instance);
        }
        return result;
    }
}