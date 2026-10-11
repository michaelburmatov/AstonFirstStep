package App.InputClasses;

public class Car {
    private String model;
    private int year;
    private String color;

    public Car() {}

    private Car(Builder b) {
        this.model = b.model;
        this.year  = b.year;
        this.color = b.color;
    }

    public String getModel() { return model; }
    public int    getYear()  { return year; }
    public String getColor() { return color; }

    @Override
    public String toString() {
        return "{Модель='" + model + "', год=" + year + ", цвет='" + color + "'}";
    }

    public static class Builder {
        private String model;
        private int year;
        private String color;
        public Builder model(String m) { this.model = m; return this; }
        public Builder year(int y)     { this.year  = y; return this; }
        public Builder color(String c) { this.color = c; return this; }
        public Car build() { return new Car(this); }
    }
}