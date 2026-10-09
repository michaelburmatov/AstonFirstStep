package App.States.Strategizing.Sorting;

import java.util.List;

public final class InsertionSortStratage<T> implements SortStratage<T> {

    @Override
    public String name() {
        return "InsertionSort";
    }

    @Override
    public List<T> doStratage(SortContext<T> context) {
        List<T> data = context.getData();
        var cmp = context.getComparator();

        for (int i = 1; i < data.size(); i++) {
            T key = data.get(i);
            int j = i - 1;
            while (j >= 0 && cmp.compare(data.get(j), key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
        return data;
    }
}