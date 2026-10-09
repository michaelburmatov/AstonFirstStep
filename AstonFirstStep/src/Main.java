import App.Debug.Debuger;
import App.Debug.Delegation.PrintlnDelegation;
import App.States.AppStateMachine;
import Architecture.GoF.Creational.Singleton;

public class Main {
    public static void main(String[] args) {
        Singleton.register(Debuger.class, new Debuger(new PrintlnDelegation()));

        var machine = new AppStateMachine();
        machine.run();
    }
}