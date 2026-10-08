package App.States;

import App.AppDataStorage;
import App.InputClasses.Car;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.Contexts.FileInputContext;
import App.States.Strategizing.FileInputStratage;
import App.States.Strategizing.HandInputStratage;

import java.util.List;
import java.util.Scanner;

public class InputState extends AppStrategizingState<List<?>> {
    public InputState(AppDataStorage storage) {
        super(storage);
        stratages.put(Scanner.class, new HandInputStratage());
        stratages.put(FileInputContext.class, new FileInputStratage());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        //var result = doStratage(new Scanner(System.in));

        var type = Car.class;
        var result = doStratage( new FileInputContext("FileInputData.txt", type));

        for(var element : result)
            debuger.Log(element);

        storage.setType(type);
        storage.setOriginalData(result);
        stateMachine.switchState(ValidationDataState.class);
    }
}
