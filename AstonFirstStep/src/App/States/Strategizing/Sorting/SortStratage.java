package App.States.Strategizing.Sorting;

import Architecture.GoF.Behavioral.StratageResult;

import java.util.List;

public interface SortStratage<T> extends StratageResult<List<T>, SortContext<T>> {
    String name();
}