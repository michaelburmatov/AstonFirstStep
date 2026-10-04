package Architecture.GoF.Behavioral;

public interface State<T extends StateMachine<T>> {
    void onEnter(T stateMachine) throws Exception;
    void onUpdate(T stateMachine) throws Exception;
    void onExit(T stateMachine) throws Exception;
}
