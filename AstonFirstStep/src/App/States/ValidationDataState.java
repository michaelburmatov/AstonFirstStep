package App.States;

import App.AppDataStorage;
import App.States.Strategizing.Validation.NotEmptyValidation;
import App.States.Strategizing.Validation.ValidationStrategy;

import java.util.List;

public class ValidationDataState extends AppState<AppStateMachine> {

    public ValidationDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        // 1) Получение данных из хранилища
        List<?> rawDataList = storage.getOriginalData();
        // Проверка на пустой список
        if (rawDataList == null || rawDataList.isEmpty()) {
            System.out.println("Нет данных для валидации. Пожалуйста, введите данные заново.");
            stateMachine.switchState(InputState.class);
            return;
        }
        // 2) Инициализация стратегии валидации 
        ValidationStrategy validator = new NotEmptyValidation();
        // 3) Проходим по всем элементам списка и проверяем их
        for (Object item : rawDataList) {
            String dataString = String.valueOf(item);
            if (!validator.isValid(dataString)) {
                // Если проверка не прошла:
                System.out.println("Валидация не пройдена!");
                System.out.println(validator.getErrorMessage());
                System.out.println("Исправьте данные и попробуйте снова.");
                // Возвращение пользователя на шаг ввода, для исправления ошибки
                stateMachine.switchState(InputState.class);
                return;
            }
        }
        System.out.println("Все данные успешно прошли валидацию!");
        // Переход к следующему этапу.
        stateMachine.switchState(ConvertDataState.class);
    }
}
