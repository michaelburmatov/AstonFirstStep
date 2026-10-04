package App.States;

import App.AppDataStorage;
import Architecture.GoF.Behavioral.StateMachine;

public class AppStateMachine extends StateMachine<AppStateMachine> {
    private AppDataStorage storage;
    public AppStateMachine(){
        states.put(InitState.class, new InitState());
        states.put(InputState.class, new InputState(storage));
        states.put(ValidationDataState.class, new ValidationDataState(storage));
        states.put(ConvertDataState.class, new ConvertDataState(storage));
        states.put(SortDataState.class, new SortDataState(storage));
        states.put(OutputDataState.class, new OutputDataState(storage));

        switchState(InitState.class);
    }
}
