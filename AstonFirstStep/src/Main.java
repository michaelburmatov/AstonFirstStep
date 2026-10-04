import App.Debug.Debuger;
import App.Debug.Delegation.PrintlnDelegation;
import App.States.AppStateMachine;
import Architecture.GoF.Creational.Singleton;

void main() {
    Singleton.register(Debuger.class, new Debuger(new PrintlnDelegation()));

    var machine = new AppStateMachine();
    machine.run();
}
