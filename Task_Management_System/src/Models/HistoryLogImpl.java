package Models;

import Models.Contracts.HistoryLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @Override
    public String viewInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedTimestamp = timestamp.format(formatter);
        return String.format("Time: %s \nEvent: %s", formattedTimestamp, description);
    }

}
