package App.States.Strategizing.Validation;

public interface ValidationStrategy {

    boolean isValid(String data);

    String getErrorMessage();
}
