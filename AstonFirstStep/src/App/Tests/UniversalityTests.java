package App.Tests;

import App.Comparators.UniversalComparators;
import App.InputClasses.Car;
import App.States.Strategizing.Sorting.BubbleSortStratage;
import App.States.Strategizing.Sorting.InsertionSortStratage;
import App.States.Strategizing.Sorting.QuickSortStratage;
import App.States.Strategizing.Sorting.SortContext;
import App.Universal.UniversalClass;
import App.Universal.UniversalField;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UniversalityTests {

    static class Person {
        final String name;
        final int age;
        Person(String name, int age) { this.name = name; this.age = age; }

        @Override
        public String toString() { return name + "(" + age + ")"; }
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("ФОРМАТ:::Тип входных данных + Тип сортировки\n");

        testIntegersBubble();
        testStringsQuick();
        testStringsInsertionByLength();
        testPersonQuick();

        testCarByModel();
        testCarByYear();
        testCarByColor();

        testUniversalByField1();
        testUniversalByField2();
        testUniversalByField3();

    }

    private static void testIntegersBubble() {
        System.out.println("\n------- 1. Integer + BubbleSort -------");
        List<Integer> ints = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 7));
        System.out.println("ввод:      " + ints);
        new BubbleSortStratage<Integer>().doStratage(new SortContext<>(ints, Comparator.naturalOrder()));
        System.out.println("результат: " + ints);
    }

    private static void testStringsQuick() {
        System.out.println("\n------- 2. String + QuickSort (по алфавиту) -------");
        List<String> words = new ArrayList<>(Arrays.asList("bmw", "audi", "kia", "opel", "vw"));
        System.out.println("ввод:      " + words);
        new QuickSortStratage<String>().doStratage(new SortContext<>(words, Comparator.naturalOrder()));
        System.out.println("результат: " + words);
    }

    private static void testStringsInsertionByLength() {
        System.out.println("\n------- 3. String + InsertionSort (по длине) -------");
        List<String> words = new ArrayList<>(Arrays.asList("bmw", "audi", "kia", "vw", "opel"));
        System.out.println("ввод:      " + words);
        new InsertionSortStratage<String>()
                .doStratage(new SortContext<>(words, Comparator.comparingInt(String::length)));
        System.out.println("результат: " + words);
    }

    private static void testPersonQuick() {
        System.out.println("\n------- 4. Person + QuickSort по возрасту -------");
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Анна", 30),
                new Person("Владимир", 25),
                new Person("Александр", 35),
                new Person("Марина", 20)
        ));
        System.out.println("ввод:      " + people);
        new QuickSortStratage<Person>()
                .doStratage(new SortContext<>(people, Comparator.comparingInt(p -> p.age)));
        System.out.println("результат: " + people);
    }

    private static void testCarByModel() {
        System.out.println("\n------- 5.1 Car + BubbleSort по model -------");
        List<Car> cars = sampleCars();
        System.out.println("ввод:      " + cars);
        new BubbleSortStratage<Car>()
                .doStratage(new SortContext<>(cars, Comparator.comparing(Car::getModel)));
        System.out.println("результат: " + cars);
    }

    private static void testCarByYear() {
        System.out.println("\n------- 5.2 Car + QuickSort по year -------");
        List<Car> cars = sampleCars();
        System.out.println("ввод:      " + cars);
        new QuickSortStratage<Car>()
                .doStratage(new SortContext<>(cars, Comparator.comparingInt(Car::getYear)));
        System.out.println("результат: " + cars);
    }

    private static void testCarByColor() {
        System.out.println("\n------- 5.3 Car + InsertionSort по color -------");
        List<Car> cars = sampleCars();
        System.out.println("ввод:      " + cars);
        new InsertionSortStratage<Car>()
                .doStratage(new SortContext<>(cars, Comparator.comparing(Car::getColor)));
        System.out.println("результат: " + cars);
    }

    private static void testUniversalByField1() {
        System.out.println("\n------- 6.1 UniversalClass + BubbleSort по 1-му полю (число) -------");
        List<UniversalClass> data = sampleUniversals();
        printUniversals("ввод:     ", data);
        new BubbleSortStratage<UniversalClass>()
                .doStratage(new SortContext<>(data, UniversalComparators.byField(0)));
        printUniversals("результат:", data);
    }

    private static void testUniversalByField2() {
        System.out.println("\n------- 6.2 UniversalClass + QuickSort по 2-му полю (строка) -------");
        List<UniversalClass> data = sampleUniversals();
        printUniversals("ввод:     ", data);
        new QuickSortStratage<UniversalClass>()
                .doStratage(new SortContext<>(data, UniversalComparators.byField(1)));
        printUniversals("результат:", data);
    }

    private static void testUniversalByField3() {
        System.out.println("\n------- 6.3 UniversalClass + InsertionSort по 3-му полю (boolean) -------");
        List<UniversalClass> data = sampleUniversals();
        printUniversals("ввод:     ", data);
        new InsertionSortStratage<UniversalClass>()
                .doStratage(new SortContext<>(data, UniversalComparators.byField(2)));
        printUniversals("результат:", data);
    }


    private static List<Car> sampleCars() {
        return new ArrayList<>(Arrays.asList(
                new Car.Builder().model("nissan").year(2000).color("green").build(),
                new Car.Builder().model("audi").year(2001).color("red").build(),
                new Car.Builder().model("bmw").year(1998).color("blue").build()
        ));
    }

    private static List<UniversalClass> sampleUniversals() {
        return new ArrayList<>(Arrays.asList(
                makeUniversal(15, "bmw", true),
                makeUniversal(3, "audi", false),
                makeUniversal(55, "kia", false)
        ));
    }

    private static UniversalClass makeUniversal(int a, String b, boolean c) {
        UniversalField<?>[] fields = new UniversalField<?>[]{
                new UniversalField<>(Integer.class, a),
                new UniversalField<>(String.class, b),
                new UniversalField<>(Boolean.class, c)
        };
        return new UniversalClass(fields);
    }

    private static void printUniversals(String prefix, List<UniversalClass> list) {
        System.out.print(prefix + " ");
        for (UniversalClass u : list) {
            System.out.print("[");
            for (int i = 0; i < u.getCount(); i++) {
                System.out.print(u.getField(i));
                if (i < u.getCount() - 1) System.out.print(", ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}