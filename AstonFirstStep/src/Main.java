import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import App.Debug.Debuger;
import App.Debug.Delegation.PrintlnDelegation;
import App.States.AppStateMachine;
import Architecture.GoF.Creational.Singleton;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Singleton.register(Debuger.class, new Debuger(new PrintlnDelegation()));

        var machine = new AppStateMachine();
        machine.run();
    }
}