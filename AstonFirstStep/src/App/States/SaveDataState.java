package App.States;

import App.AppDataStorage;
import App.Universal.UniversalClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.StringJoiner;

public class SaveDataState extends AppState<AppStateMachine> {
    public SaveDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);

        List<UniversalClass> data = storage.getConvertedData();

        if (data == null || data.isEmpty()) {
            System.out.println("Нет данных для сохранения");
            stateMachine.stop();
            return;
        }

        Path file = Path.of("result.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            for (UniversalClass item : data) {
                StringJoiner line = new StringJoiner(", ");

                for (int i = 0; i < item.getCount(); i++) {
                    line.add(String.valueOf(item.getField(i)));
                }

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Не удалось сохранить данные: " + e.getMessage());
            stateMachine.stop();
            return;
        }

        System.out.println("Данные сохранены в " + file.toAbsolutePath());
        stateMachine.switchState(InputState.class);
    }
}
