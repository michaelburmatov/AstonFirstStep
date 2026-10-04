package App.Debug.Delegation;

public class PrintlnDelegation implements LogDelegation{

    @Override
    public void Log(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void Log(String string) {
        System.out.println(string);
    }
}
