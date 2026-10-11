package App.States.Strategizing.Sorting;

import java.util.List;

public final class BubbleSortStratage<T> implements SortStratage<T> {

    @Override
    public String name() {
        return "BubbleSort";
    }

    @Override
    public List<T> doStratage(SortContext<T> context) {
        List<T> data = context.getData();
        var cmp = context.getComparator();
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (cmp.compare(data.get(j), data.get(j + 1)) > 0) {
                    T tmp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return data;
    }
}