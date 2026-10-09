package App.States;

import App.AppDataStorage;
import App.Comparators.UniversalComparators;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.Sorting.*;
import App.Universal.UniversalClass;

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

        var context = new SortContext<>(
                data,
                UniversalComparators.byField(0));

        var sorted = doStratage(QuickSortStratage.class, context);
        storage.setConvertedData(sorted);

        stateMachine.switchState(OutputDataState.class);
    }
}