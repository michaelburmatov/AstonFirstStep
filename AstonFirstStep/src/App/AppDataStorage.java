package App;

import App.Universal.UniversalClass;

import java.util.List;

public class AppDataStorage {
    private Class type;
    private List<?> originalData;
    private List<UniversalClass> convertedData;

    public List<UniversalClass> getConvertedData() {
        return convertedData;
    }

    public void setConvertedData(List<UniversalClass> convertedData) {
        this.convertedData = convertedData;
    }

    public List<?> getOriginalData() {
        return originalData;
    }

    public void setOriginalData(List<?> originalData) {
        this.originalData = originalData;
    }
    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

}
