package App.Tests;

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
        System.out.println("------- 1. Integer + BubbleSort -------");
        List<Integer> ints = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 7));
        System.out.println("ввод: " + ints);
        new BubbleSortStratage<Integer>().doStratage(new SortContext<>(ints, Comparator.naturalOrder()));
        System.out.println("результат:  " + ints);

        System.out.println("\n------- 2. String + QuickSort (по алфавиту) -------");
        List<String> words = new ArrayList<>(Arrays.asList("bmw", "audi", "kia", "opel", "vw"));
        System.out.println("ввод: " + words);
        new QuickSortStratage<String>().doStratage(new SortContext<>(words, Comparator.naturalOrder()));
        System.out.println("результат:  " + words);

        System.out.println("\n------- 3. String + InsertionSort (по длине) -------");
        List<String> words2 = new ArrayList<>(Arrays.asList("bmw", "audi", "kia", "vw", "opel"));
        System.out.println("ввод: " + words2);
        new InsertionSortStratage<String>().doStratage(new SortContext<>(words2, Comparator.comparingInt(String::length)));
        System.out.println("результат:  " + words2);

        System.out.println("\n------- 4. Person + QuickSort по возрасту -------");
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Анна", 30),
                new Person("Владимир",   25),
                new Person("Александр", 35),
                new Person("Марина",  20)
        ));
        System.out.println("ввод: " + people);
        new QuickSortStratage<Person>().doStratage(new SortContext<>(people, Comparator.comparingInt(p -> p.age)));
        System.out.println("результат:  " + people);

        System.out.println("\n------- 5.1 UniversalClass + BubbleSort по 1-му полю -------");
        List<UniversalClass> universals = new ArrayList<>(Arrays.asList(
                makeUniversal(15, "bmw",  true),
                makeUniversal(3,  "audi", false),
                makeUniversal(55, "kia",  true)
        ));
        printUniversals("ввод:", universals);
        new BubbleSortStratage<UniversalClass>().doStratage(new SortContext<>(universals, byField(0)));
        printUniversals("результат: ", universals);

        System.out.println("\n------- 5.2 UniversalClass + BubbleSort по 2-му полю -------");
        List<UniversalClass> universals2 = new ArrayList<>(Arrays.asList(
                makeUniversal(15, "bmw",  true),
                makeUniversal(55, "kia",  true),
                makeUniversal(101,  "audi", false)
        ));
        printUniversals("ввод:", universals2);
        new BubbleSortStratage<UniversalClass>().doStratage(new SortContext<>(universals2, byField(1)));
        printUniversals("результат: ", universals2);

        System.out.println("\n------- 5.3 UniversalClass + BubbleSort по 3-му полю -------");
        List<UniversalClass> universals3 = new ArrayList<>(Arrays.asList(
                makeUniversal(15, "bmw",  true),
                makeUniversal(3,  "audi", false),
                makeUniversal(55, "kia",  false)
        ));
        printUniversals("ввод:", universals3);
        new BubbleSortStratage<UniversalClass>().doStratage(new SortContext<>(universals3, byField(2)));
        printUniversals("результат: ", universals3);
    }

    private static UniversalClass makeUniversal(int a, String b, boolean c) {
        UniversalField<?>[] fields = new UniversalField<?>[]{
                new UniversalField<>(Integer.class, a),
                new UniversalField<>(String.class,  b),
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Comparator<UniversalClass> byField(int index) {
        return (a, b) -> ((Comparable) a.getField(index))
                .compareTo(b.getField(index));
    }
}