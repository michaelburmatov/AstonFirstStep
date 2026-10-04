package App.States;

import App.AppDataStorage;
import Architecture.GoF.Behavioral.StateMachine;

public class AppState <T extends StateMachine<T>> extends StandardState<T> {
    protected AppDataStorage storage;
    public AppState(AppDataStorage storage)
    {
        this.storage = storage;
    }
}
