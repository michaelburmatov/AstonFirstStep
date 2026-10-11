package App.States;

import App.AppDataStorage;
import App.Comparators.UniversalComparators;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.Sorting.BubbleSortStratage;
import App.States.Strategizing.Sorting.InsertionSortStratage;
import App.States.Strategizing.Sorting.QuickSortStratage;
import App.States.Strategizing.Sorting.SortContext;
import App.Universal.UniversalClass;

import java.util.Comparator;
import java.util.List;

public class SortDataState extends AppStrategizingState<List<UniversalClass>> {

    public SortDataState(AppDataStorage storage) {
        super(storage);

        stratages.put(BubbleSortStratage.class,    new BubbleSortStratage<>());
        stratages.put(QuickSortStratage.class,     new QuickSortStratage<>());
        stratages.put(InsertionSortStratage.class, new InsertionSortStratage<>());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        List<UniversalClass> data = storage.getConvertedData();
        if (data == null || data.isEmpty()) {
            stateMachine.switchState(OutputDataState.class);
            return;
        }

        int field = storage.getSortFieldIndex();
        Comparator<UniversalClass> comparator = storage.isSortDescending()
                ? UniversalComparators.byFieldDesc(field)
                : UniversalComparators.byField(field);

        Class<?> algorithm = storage.getSortAlgorithm() != null
                ? storage.getSortAlgorithm()
                : QuickSortStratage.class;

        var context = new SortContext<>(data, comparator);
        var sorted  = doStratage(algorithm, context);

        storage.setConvertedData(sorted);
        stateMachine.switchState(OutputDataState.class);
    }
}