package App;

import App.Universal.UniversalClass;

import java.util.ArrayList;
import java.util.List;

public class AppDataStorage {

    private Class<?> type;
    private List<?> originalData = new ArrayList<>();
    private List<UniversalClass> convertedData = new ArrayList<>();

    private int sortFieldIndex = 0;
    private Class<?> sortAlgorithm = null;
    private boolean sortDescending = false;

    public AppDataStorage() {}

    public AppDataStorage(List<?> dataList) {
        if (dataList != null) this.originalData = dataList;
    }

    public Class<?> getType() { return type; }
    public void setType(Class<?> type) { this.type = type; }

    public List<?> getOriginalData() { return originalData; }
    public void setOriginalData(List<?> originalData) { this.originalData = originalData; }

    public List<UniversalClass> getConvertedData() { return convertedData; }
    public void setConvertedData(List<UniversalClass> convertedData) { this.convertedData = convertedData; }

    public int getSortFieldIndex() { return sortFieldIndex; }
    public void setSortFieldIndex(int sortFieldIndex) { this.sortFieldIndex = sortFieldIndex; }

    public Class<?> getSortAlgorithm() { return sortAlgorithm; }
    public void setSortAlgorithm(Class<?> sortAlgorithm) { this.sortAlgorithm = sortAlgorithm; }

    public boolean isSortDescending() { return sortDescending; }
    public void setSortDescending(boolean sortDescending) { this.sortDescending = sortDescending; }
}