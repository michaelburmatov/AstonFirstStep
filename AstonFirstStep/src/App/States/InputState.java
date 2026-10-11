package App.States;

import App.AppDataStorage;
import App.InputClasses.Car;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.Contexts.FileInputContext;
import App.States.Strategizing.FileInputStratage;
import App.States.Strategizing.HandInputStratage;

import java.util.List;
import java.util.Scanner;

public class InputState extends AppStrategizingState<List<?>> {

    private final Scanner scanner = new Scanner(System.in);

    public InputState(AppDataStorage storage) {
        super(storage);
        stratages.put(Scanner.class, new HandInputStratage(storage));
        stratages.put(FileInputContext.class, new FileInputStratage());
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        System.out.print("Источник (1 - вручную, 2 - из файла, 3 - выход): ");
        String choice = scanner.nextLine().trim();

        Class<?> type = Car.class;

        switch (choice) {
            case "1": {
                storage.setType(type);
                var result = doStratage(scanner);
                if (result == null) { stateMachine.stop(); return; }
                storage.setOriginalData(result);
                break;
            }
            case "2": {
                System.out.print("Путь к файлу (Enter — FileInputData.txt): ");
                String path = scanner.nextLine().trim();
                var result = doStratage(new FileInputContext(path, type));
                storage.setType(type);
                storage.setOriginalData(result);
                break;
            }
            case "3":
                stateMachine.stop();
                return;
            default:
                System.out.println("Неизвестный выбор");
                return;
        }

        stateMachine.switchState(ValidationDataState.class);
    }
}