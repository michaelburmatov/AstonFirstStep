package App.States;

import App.AppDataStorage;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.FileInputStratage;
import App.States.Strategizing.HandInputStratage;
import App.Universal.ClassContainer;

import java.util.List;
import java.util.Scanner;

public class InputState extends AppStrategizingState<List<ClassContainer>> {
    public InputState(AppDataStorage storage) {
        super(storage);
        stratages.put(Scanner.class, new HandInputStratage());
        stratages.put(String.class, new FileInputStratage());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        //var result = doStratage(new Scanner(System.in));
        var result = doStratage("FileInputData.txt");
        for(var element : result)
            debuger.Log(element);

        stateMachine.switchState(ValidationDataState.class);
    }
}
