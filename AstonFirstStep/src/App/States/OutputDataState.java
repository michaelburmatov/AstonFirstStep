package App.States;

import App.AppDataStorage;

public class OutputDataState extends AppState<AppStateMachine>{
    public OutputDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        stateMachine.stop();
    }
}
