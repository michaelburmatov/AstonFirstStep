package App.States;

import App.AppDataStorage;
import App.DataConvert.ConverterToUniversal;
import App.Proxy.TestClass;

public class ConvertDataState extends AppState<AppStateMachine> {

    public ConvertDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onEnter(AppStateMachine stateMachine) throws Exception {
        super.onEnter(stateMachine);

        //---------------------Пример конвертера в универсальный класс----------------
        var builder = new ConverterToUniversal<TestClass>();
        builder.append(new TestClass(12, "323", false));
        builder.append(new TestClass(5435, "hfghfgh", true));
        builder.append(new TestClass(8216, "75hgfh", false));
        builder.append(new TestClass(41, "-", true));

        var result = builder.build();

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
