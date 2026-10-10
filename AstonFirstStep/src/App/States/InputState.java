package App.States;

import App.AppDataStorage;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.HandInputStratage;

import java.util.List;
import java.util.Scanner;

public class InputState extends AppStrategizingState<List<?>> {

    private final Scanner scanner = new Scanner(System.in);

    public InputState(AppDataStorage storage) {
        super(storage);
        stratages.put(Scanner.class, new HandInputStratage());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        var result = doStratage(scanner);

        if (result == null) {
            stateMachine.stop();
            return;
        }

        storage.setOriginalData(result);
        stateMachine.switchState(ValidationDataState.class);
    }
}