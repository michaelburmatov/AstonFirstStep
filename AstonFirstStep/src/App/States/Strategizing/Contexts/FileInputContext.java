package App.States.Strategizing.Contexts;

public class FileInputContext {
    private final Class<?> type;
    private final String path;

    public FileInputContext(String path, Class<?> type) {
        this.path = path;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public String getPath() {
        return path;
    }
}