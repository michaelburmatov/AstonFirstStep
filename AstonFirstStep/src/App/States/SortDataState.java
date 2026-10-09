package App.States;

import App.AppDataStorage;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.Sorting.*;
import App.Universal.UniversalClass;

import java.util.List;

public class SortDataState extends AppStrategizingState<List<UniversalClass>> {

    public SortDataState(AppDataStorage storage) {
        super(storage);

        stratages.put(BubbleSortStratage.class, new BubbleSortStratage<>());
        stratages.put(QuickSortStratage.class, new QuickSortStratage<>());
        stratages.put(InsertionSortStratage.class, new InsertionSortStratage<>());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        List<UniversalClass> data = storage.getConvertedData();

        // Реализовать выбор поля: 0, 1, 2
        java.util.Comparator<UniversalClass> comparator = (a, b) -> 0;

        SortContext<UniversalClass> context = new SortContext<>(data, comparator);

        // List<UniversalClass> sorted = doStratage(QuickSortStratage.class, context);

        // storage.setConvertedData(sorted);

        stateMachine.switchState(OutputDataState.class);
    }
}