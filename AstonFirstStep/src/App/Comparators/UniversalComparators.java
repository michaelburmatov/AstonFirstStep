package App.Comparators;

import App.Universal.UniversalClass;

import java.util.Comparator;

public final class UniversalComparators {

    private UniversalComparators() {}

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Comparator<UniversalClass> byField(int index) {
        return (a, b) -> ((Comparable) a.getField(index))
                .compareTo(b.getField(index));
    }

    public static Comparator<UniversalClass> byFieldDesc(int index) {
        return byField(index).reversed();
    }
}