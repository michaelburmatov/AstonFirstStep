package App.States.Strategizing;

import App.AppDataStorage;
import App.States.AppState;
import App.States.AppStateMachine;
import Architecture.GoF.Behavioral.StratageResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AppStrategizingState<R> extends AppState<AppStateMachine> {

    protected Map<Class<?>, StratageResult<R, ?>> stratages = new ConcurrentHashMap<>();

    public AppStrategizingState(AppDataStorage storage) {
        super(storage);
    }

    // Ключ = класс контекста - для состояний с одной стратегией
    @SuppressWarnings("unchecked")
    protected <C> R doStratage(C context) throws Exception {
        var stratage = stratages.get(context.getClass());
        if (stratage == null)
            throw new Exception("Stratage " + context.getClass() + " is non found!");
        return ((StratageResult<R, C>) stratage).doStratage(context);
    }

    // Ключ задаётся явно - для состояний с несколькими стратегиями - нужно будет для мульти-вызова методов сортировки
    @SuppressWarnings("unchecked")
    protected <C> R doStratage(Class<?> key, C context) throws Exception {
        var stratage = stratages.get(key);
        if (stratage == null)
            throw new Exception("Stratage " + key + " is non found!");
        return ((StratageResult<R, C>) stratage).doStratage(context);
    }
}