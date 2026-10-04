package App.States;

import App.AppDataStorage;

public class ValidationDataState extends AppState<AppStateMachine>{
    public ValidationDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        stateMachine.switchState(ConvertDataState.class);
    }
}
