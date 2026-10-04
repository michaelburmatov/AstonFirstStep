package App.States;

import App.AppDataStorage;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.HandInputStratage;

import java.util.List;
import java.util.Scanner;

public class InputState extends AppStrategizingState<List<?>> {
    public InputState(AppDataStorage storage) {
        super(storage);
        stratages.put(Scanner.class, new HandInputStratage());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        var result = doStratage(new Scanner(System.in));
        stateMachine.switchState(ValidationDataState.class);
    }
}
