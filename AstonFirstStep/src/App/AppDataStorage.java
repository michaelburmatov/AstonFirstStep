package App;

import App.Universal.UniversalClass;

import java.util.ArrayList;
import java.util.List;

public class AppDataStorage {

    private List<?> originalData;
    private List<UniversalClass> convertedData;

    private int sortFieldIndex = 0;
    private Class<?> sortAlgorithm = null;
    private boolean sortDescending = false;

    public AppDataStorage(List<?> dataList) {
        this.originalData  = dataList != null ? dataList : new ArrayList<>();
        this.convertedData = new ArrayList<>();
    }

    public List<UniversalClass> getConvertedData() { return convertedData; }
    public void setConvertedData(List<UniversalClass> convertedData) { this.convertedData = convertedData; }

    public List<?> getOriginalData() { return originalData; }
    public void setOriginalData(List<?> originalData) { this.originalData = originalData; }

    public int getSortFieldIndex() { return sortFieldIndex; }
    public void setSortFieldIndex(int sortFieldIndex) { this.sortFieldIndex = sortFieldIndex; }

    public Class<?> getSortAlgorithm() { return sortAlgorithm; }
    public void setSortAlgorithm(Class<?> sortAlgorithm) { this.sortAlgorithm = sortAlgorithm; }

    public boolean isSortDescending() { return sortDescending; }
    public void setSortDescending(boolean sortDescending) { this.sortDescending = sortDescending; }
}