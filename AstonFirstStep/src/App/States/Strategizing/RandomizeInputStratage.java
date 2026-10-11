package App.States.Strategizing;

import App.InputClasses.Car;
import Architecture.GoF.Behavioral.StratageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizeInputStratage implements StratageResult<List<?>, Integer> {

    private final Random random = new Random();

    @Override
    public List<?> doStratage(Integer count) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cars.add(new Car.Builder()
                    .model("model-" + random.nextInt(100))
                    .year(2000 + random.nextInt(25))
                    .color("color-" + random.nextInt(10))
                    .build());
        }
        return cars;
    }
}
