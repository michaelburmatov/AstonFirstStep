package App.States;

import App.AppDataStorage;
import App.DataConvert.ConverterToUniversal;
import App.Universal.UniversalClass;

import java.util.List;

public class ConvertDataState extends AppState<AppStateMachine> {

    public ConvertDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onEnter(AppStateMachine stateMachine) throws Exception {
        super.onEnter(stateMachine);

        List<UniversalClass> result = convert(storage.getType(), storage.getOriginalData());
        storage.setConvertedData(result);

        for (var res : result) {
            debuger.Log(res);
        }
    }

    private <T> List<UniversalClass> convert(Class<T> type, List<?> data) {
        var converter = new ConverterToUniversal<T>(type);
        if (data != null) {
            for (Object object : data) {
                converter.append(type.cast(object));
            }
        }
        return converter.build();
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        stateMachine.switchState(SortDataState.class);
    }
}