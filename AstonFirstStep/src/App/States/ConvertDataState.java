package App.States;

import App.AppDataStorage;
import App.DataConvert.ConverterToUniversal;

import java.util.List;

public class ConvertDataState extends AppState<AppStateMachine> {

    public ConvertDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onEnter(AppStateMachine stateMachine) throws Exception {
        super.onEnter(stateMachine);

        var converter = new ConverterToUniversal<>(storage.getType());

        List<?> data = storage.getOriginalData();
        for (var object : data) {
            converter.append(object);
        }

        var result = converter.build();
        storage.setConvertedData(result);

        for(var res : result) {
            debuger.Log(res);
        }
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        stateMachine.switchState(SortDataState.class);
    }
}
