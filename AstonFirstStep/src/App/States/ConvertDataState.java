package App.States;

import App.AppDataStorage;
import App.DataConvert.ConverterToUniversal;
import App.Proxy.TestClass;

public class ConvertDataState extends AppState<AppStateMachine> {

    public ConvertDataState(AppDataStorage storage) {
        super(storage);
    }

    @Override
    public void onEnter(AppStateMachine stateMachine) throws Exception {
        super.onEnter(stateMachine);

        var builder = new ConverterToUniversal<TestClass>();

        var original = storage.getOriginalData();
        if (original != null) {
            for (var raw : original) {
                var parsed = parseTestClass(String.valueOf(raw));
                if (parsed != null) {
                    builder.append(parsed);
                }
            }
        }

        storage.setConvertedData(builder.build());
    }

    private TestClass parseTestClass(String raw) {
        var parts = raw.split(",");
        if (parts.length != 3) return null;
        try {
            Integer first  = Integer.parseInt(parts[0].trim());
            String  second = parts[1].trim();
            Boolean third  = Boolean.parseBoolean(parts[2].trim());
            return new TestClass(first, second, third);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void onUpdate(AppStateMachine stateMachine) throws Exception {
        super.onUpdate(stateMachine);
        stateMachine.switchState(SortDataState.class);
    }
}