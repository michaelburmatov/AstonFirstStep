package App.States;

import App.AppDataStorage;
import App.States.Strategizing.Sorting.SortContext;
import App.Universal.UniversalClass;
import App.Universal.UniversalField;

import java.util.*;

public class SpecialSortDataState extends SortDataState{
    public SpecialSortDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        //super.onUpdate(stateMachine);
        var listData = storage.getConvertedData();
        if (listData == null || listData.isEmpty()) {
            stateMachine.switchState(OutputDataState.class);
            return;
        }

        var original = listData.toArray(new UniversalClass[0]);
        int naturalIndex = 0;
        if(original.length > 0) {
            naturalIndex = getHaveNaturalFieldIndex(original[0]);
            if(naturalIndex == -1){
                System.out.println("Универсальный класс не содержит поля с натуральными числами!");
                stateMachine.switchState(OutputDataState.class);
                return;
            }
        }

        var listEvenNumbers = new ArrayList<UniversalClass>();
        boolean[] isEvenIndex = new boolean[original.length];
        for (int i = 0; i < original.length; i++) {
            extracted(original, i, naturalIndex, isEvenIndex, listEvenNumbers);
        }

        int field = storage.getSortFieldIndex();
        Comparator<UniversalClass> comparator = getComparator(field);
        Class<?> algorithm = getAlgorithm();

        var context = new SortContext<>(listEvenNumbers, comparator);
        var sorted  = doStratage(algorithm, context);
        Queue<UniversalClass> queueData = new ArrayDeque<>(sorted);

        var resultList = new ArrayList<UniversalClass>();
        for(var i = 0; i < original.length; i++) {
            var element = original[i];
            if(isEvenIndex[i]) {
                resultList.add(queueData.poll());
                continue;
            }
            resultList.add(element);
        }

        storage.setConvertedData(resultList);
        stateMachine.switchState(OutputDataState.class);
    }

    private static void extracted(UniversalClass[] original, int i, int naturalIndex, boolean[] isEvenIndex, ArrayList<UniversalClass> listEvenNumbers) {
        var element = original[i];
        var value = element.getField(naturalIndex);
        if (value != null && ((Number) value).longValue() % 2 == 0) {
            isEvenIndex[i] = true;
            listEvenNumbers.add(element);
        }
    }


    private int getHaveNaturalFieldIndex(UniversalClass element) {
        for(var y = 0; y < element.getCount(); y++) {
            var type = element.getFieldType(y);
            if (type == int.class || type == Integer.class
                    || type == long.class || type == Long.class
                    || type == short.class || type == Short.class
                    || type == byte.class || type == Byte.class) {
                return y;
            }
        }

        return -1;
    }
}
