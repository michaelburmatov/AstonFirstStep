package App.States;

import App.Debug.Debuger;
import Architecture.GoF.Behavioral.State;
import Architecture.GoF.Behavioral.StateMachine;
import Architecture.GoF.Creational.Singleton;

public class StandardState<T extends StateMachine<T>> implements State<T> {
    protected Debuger debuger;
    public StandardState()
    {
        debuger = Singleton.get(Debuger.class);
    }

    @Override
    public void onEnter(T stateMachine) throws Exception {
        debuger.Log("State " + this.getClass() + " is Enter" );
    }

    @Override
    public void onUpdate(T stateMachine) throws Exception {
        debuger.Log("State " + this.getClass() + " is Update" );
    }

    @Override
    public void onExit(T stateMachine) throws Exception {
        debuger.Log("State " + this.getClass() + " is Exit" );
    }
}
