package Models;

import java.time.LocalDateTime;

public class HistoryLog {
    public static final String EMPTY_DESCR_ERR = "Description cannot be empty";
    private final String description;
    private final LocalDateTime timestamp;

    public HistoryLog(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_DESCR_ERR);
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public String viewInfo() {
        return String.format("Time:%s ,Event:%s", timestamp, description);
    }

}
