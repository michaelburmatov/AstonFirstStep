package App.States;

import App.AppDataStorage;

public class SortDataState extends AppState<AppStateMachine>{
    public SortDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine)  throws Exception{
        super.onUpdate(stateMachine);
        stateMachine.switchState(OutputDataState.class);
    }
}
