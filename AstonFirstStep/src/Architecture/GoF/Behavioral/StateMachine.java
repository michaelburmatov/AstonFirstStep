package Architecture.GoF.Behavioral;

import java.util.HashMap;
import java.util.Map;

public abstract class StateMachine<T extends StateMachine<T>> {
    protected Map<Class<? extends State<T>>, State<T>> states = new HashMap<>();
    protected State<T> activeState;
    private boolean isRuning;
    private T myself;

    public StateMachine(){
        myself = (T) this;
    }

    public void run() {
        if(isRuning)
            return;

        isRuning = true;
        while (isRuning)
            activeStateUpdate();
    }

    public void stop() {
        isRuning = false;
    }

    public <Y> void switchState(Class<Y> stateClass) {
        State<T> nextState = states.get(stateClass);

        if (nextState == null) {
            //log
            return;
        }

        if(activeState != null)
            activeStateExit();

        activeState = nextState;
        activeStateEnter();
    }

    private void activeStateEnter(){
        try{
            activeState.onEnter(myself);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    private void activeStateUpdate(){
        try{
            activeState.onUpdate(myself);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    private void activeStateExit(){
        try{
            activeState.onExit(myself);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
