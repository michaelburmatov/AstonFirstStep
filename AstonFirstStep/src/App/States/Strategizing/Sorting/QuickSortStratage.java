package App.States.Strategizing.Sorting;

import java.util.Comparator;
import java.util.List;

public final class QuickSortStratage<T> implements SortStratage<T> {

    @Override
    public String name() {
        return "QuickSort";
    }

    @Override
    public List<T> doStratage(SortContext<T> context) {
        List<T> data = context.getData();
        quickSort(data, 0, data.size() - 1, context.getComparator());
        return data;
    }

    private void quickSort(List<T> data, int low, int high, Comparator<? super T> cmp) {
        if (low >= high) return;
        int p = partition(data, low, high, cmp);
        quickSort(data, low, p - 1, cmp);
        quickSort(data, p + 1, high, cmp);
    }

    private int partition(List<T> data, int low, int high, Comparator<? super T> cmp) {
        T pivot = data.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (cmp.compare(data.get(j), pivot) <= 0) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, high);
        return i + 1;
    }

    private void swap(List<T> data, int i, int j) {
        T tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }
}