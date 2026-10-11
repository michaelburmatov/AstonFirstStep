package App.States;

import App.AppDataStorage;
import App.InputClasses.Car;
import App.States.Strategizing.AppStrategizingState;
import App.States.Strategizing.Contexts.FileInputContext;
import App.States.Strategizing.FileInputStratage;
import App.States.Strategizing.HandInputStratage;
import App.States.Strategizing.Sorting.BubbleSortStratage;
import App.States.Strategizing.Sorting.InsertionSortStratage;
import App.States.Strategizing.Sorting.QuickSortStratage;

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
        List<?> result;

        switch (choice) {
            case "1": {
                storage.setType(type);
                result = doStratage(scanner);
                if (result == null) { stateMachine.stop(); return; }
                break;
            }
            case "2": {
                System.out.print("Путь к файлу (Enter — FileInputData.txt): ");
                String path = scanner.nextLine().trim();
                result = doStratage(new FileInputContext(path, type));
                storage.setType(type);
                break;
            }
            case "3":
                stateMachine.stop();
                return;
            default:
                System.out.println("Неизвестный выбор");
                return;
        }

        storage.setOriginalData(result);

        askSortOptions();

        stateMachine.switchState(ValidationDataState.class);
    }

    private void askSortOptions() {
        System.out.print("Поле для сортировки (0/1/2): ");
        storage.setSortFieldIndex(parseIntSafe(scanner.nextLine(), 0));

        System.out.print("Направление (1 - возрастание, 2 - убывание): ");
        storage.setSortDescending("2".equals(scanner.nextLine().trim()));

        System.out.print("Алгоритм (1 - пузырёк, 2 - быстрая, 3 - вставками): ");
        storage.setSortAlgorithm(pickAlgorithm(scanner.nextLine().trim()));
    }

    private int parseIntSafe(String s, int fallback) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return fallback; }
    }

    private Class<?> pickAlgorithm(String choice) {
        switch (choice) {
            case "1": return BubbleSortStratage.class;
            case "3": return InsertionSortStratage.class;
            case "2":
            default:  return QuickSortStratage.class;
        }
    }
}