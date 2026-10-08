package App.States;

import App.AppDataStorage;
import App.DataConvert.ConverterToUniversal;
import App.Proxy.TestClass;

import java.util.List;

public class ConvertDataState extends AppState<AppStateMachine> {

    public ConvertDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onEnter(AppStateMachine stateMachine) throws Exception {
        super.onEnter(stateMachine);

        List<?> data = storage.getOriginalData();

        var converter = new ConverterToUniversal<Object>();

        for (var object : data) {
            converter.append(object);
        }

        var result = converter.build();

        storage.setConvertedData(result);

        for(var res : result) {
            debuger.Log(TestClass.class);
            var count = res.getCount();
            for (var i = 0; i < count; i++) {
                debuger.Log(res.getField(i));
            }
        }
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        stateMachine.switchState(SortDataState.class);
    }
}
