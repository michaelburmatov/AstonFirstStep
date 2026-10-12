package App.States;

import App.AppDataStorage;
import App.Concurrency.OccurrenceCounter;
import App.InputClasses.Car;
import App.Universal.UniversalClass;

import java.util.List;
import java.util.Scanner;

public class OutputDataState extends AppState<AppStateMachine> {

    private final Scanner scanner = new Scanner(System.in);

    public OutputDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        System.out.println("***Отсортированный результат***");
        for (UniversalClass item : storage.getConvertedData()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < item.getCount(); i++) {
                sb.append(item.getField(i));
                if (i < item.getCount() - 1) sb.append(", ");
            }
            System.out.println(sb);
        }

        countOccurrences();

        stateMachine.switchState(InputState.class);
    }

    private void countOccurrences() throws InterruptedException {
        System.out.print("Посчитать вхождения (1 - да, 2 - пропустить): ");
        if (!"1".equals(scanner.nextLine().trim())) return;

        System.out.print("Какую модель машины искать: ");
        String target = scanner.nextLine().trim();

        System.out.print("Сколько потоков использовать: ");
        int threads;
        try {
            threads = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            threads = 2;
        }

        List<UniversalClass> data = storage.getConvertedData();

        int count = OccurrenceCounter.count(
                data,
                item -> target.equalsIgnoreCase(String.valueOf(item.getField(0))),
                threads
        );

        System.out.println("Найдено вхождений '" + target + "': " + count
                + " (потоков: " + threads + ")");
    }
}