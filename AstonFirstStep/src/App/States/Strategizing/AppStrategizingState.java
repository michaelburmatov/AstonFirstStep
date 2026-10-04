package App.States.Strategizing;

import App.AppDataStorage;
import App.States.AppState;
import App.States.AppStateMachine;
import Architecture.GoF.Behavioral.StratageResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AppStrategizingState<R> extends AppState<AppStateMachine> {

    protected Map<Class<?>, StratageResult<R, ?>> stratages = new ConcurrentHashMap<>();

    public AppStrategizingState(AppDataStorage storage) {
        super(storage);
    }

    protected <C> R doStratage(C context) throws Exception {
        var stratage = stratages.get(context.getClass());
        if(stratage == null)
            throw new Exception("Stratage " + context.getClass() + " is non found!");

        if(context instanceof C)
            return ((StratageResult<R, C>)stratage).doStratage(context);

        throw new Exception("Cant find stratage to type: " + context.getClass());
    }
}
