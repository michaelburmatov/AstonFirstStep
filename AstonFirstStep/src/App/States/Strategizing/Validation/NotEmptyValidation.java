package App.States.Strategizing.Validation;

public class NotEmptyValidation implements ValidationStrategy {
    
    private String lastCheckedValue;

    @Override
    public boolean isValid(String data) {
        this.lastCheckedValue = data;
        return data != null && !data.trim().isEmpty();
    }

    @Override
    public String getErrorMessage() {
        if (lastCheckedValue == null) {
            return "Ошибка: Данные отсутствуют (null).";
        }
        if (lastCheckedValue.trim().isEmpty()) {
            return "Ошибка: Введенные данные не могут состоять только из пробелов.";
        }
        return "";
    }
}
