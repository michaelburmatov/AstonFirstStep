package App.InputClasses;

public class Car {
    private String model;
    private int year;
    private String color;

    public Car() {}

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}
