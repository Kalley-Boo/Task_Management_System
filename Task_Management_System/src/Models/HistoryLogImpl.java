package Models;

import Models.Contracts.HistoryLog;

import java.time.LocalDateTime;

public class HistoryLogImpl implements HistoryLog {
    private final String description;
    private final LocalDateTime timestamp;

    public HistoryLogImpl(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
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
