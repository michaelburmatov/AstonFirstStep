package App.States;

import App.AppDataStorage;
import App.Universal.UniversalClass;

public class OutputDataState extends AppState<AppStateMachine> {

    public OutputDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        System.out.println("***\nОтсортированный результат***\n");
        for (UniversalClass item : storage.getConvertedData()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < item.getCount(); i++) {
                sb.append(item.getField(i));
                if (i < item.getCount() - 1) sb.append(", ");
            }
            System.out.println(sb);
        }

        stateMachine.stop();
    }
}