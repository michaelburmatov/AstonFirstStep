package App;

import App.Universal.UniversalClass;

import java.util.List;

public class AppDataStorage {
    private List<?> originalData;
    private List<UniversalClass> convertedData;

    public AppDataStorage(List<?> dataList) {
        this.originalData = dataList;
    }

    public List<UniversalClass> getConvertedData() {
        return convertedData;
    }

    public void setConvertedData(List<UniversalClass> convertedData) {
        this.convertedData = convertedData;
    }

    public List<?> getOriginalData() {
        return originalData;
    }
}
