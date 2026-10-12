package App.States.Strategizing.Sorting;

import java.util.Comparator;
import java.util.List;

public final class SortContext<T> {

    private final List<T> data;
    private final Comparator<? super T> comparator;

    public SortContext(List<T> data, Comparator<? super T> comparator) {
        this.data = data;
        this.comparator = comparator;
    }

    public List<T> getData() { return data; }
    public Comparator<? super T> getComparator() { return comparator; }
}