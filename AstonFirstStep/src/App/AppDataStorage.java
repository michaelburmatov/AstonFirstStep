package App;

import java.util.ArrayList;
import App.Universal.UniversalClass;

import java.util.List;

public class AppDataStorage {
    private List<?> originalData;
    private List<UniversalClass> convertedData;

    public AppDataStorage(List<?> dataList) {
        this.originalData  = dataList != null ? dataList : new ArrayList<>();
        this.convertedData = new ArrayList<>();
    }

    public List<UniversalClass> getConvertedData() {
        return convertedData;
    }

    public void setConvertedData(List<UniversalClass> convertedData) {
        this.convertedData = convertedData;
    }

    public void setOriginalData(List<?> originalData) { this.originalData = originalData; }

    public List<?> getOriginalData() {
        return originalData;
    }
}
