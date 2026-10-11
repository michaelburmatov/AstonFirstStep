package App.States;

import App.AppDataStorage;
import App.Universal.UniversalClass;

public class SpecialSortDataState extends SortDataState{
    public SpecialSortDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        //super.onUpdate(stateMachine);
        var listData = storage.getConvertedData();
        var original = listData.toArray(new UniversalClass[0]);
        for(var i = 0; i < original.length; i++) {
            var element = original[i];
            for(var y = 0; y < element.getCount(); y++) {
                var fieldType = element.getFieldType(y);
                //if(fieldType)
            }
        }
    }
}
