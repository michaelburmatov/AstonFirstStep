package App.Debug;

import App.Debug.Delegation.LogDelegation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Debuger {
    private static final DateTimeFormatter LOG_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ");

    private final LogDelegation logger;
    private boolean isActive;

    public Debuger(LogDelegation logger) {
        this.logger = logger;
        isActive = true;
    }

    public void Log(Object obj) {
        if(isActive){
            String timestamp = LocalDateTime.now().format(LOG_FORMATTER);
            logger.Log(timestamp + obj);
        }
    }

    public void Log(String string) {
        if(isActive) {
            String timestamp = LocalDateTime.now().format(LOG_FORMATTER);
            logger.Log(timestamp + string);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
