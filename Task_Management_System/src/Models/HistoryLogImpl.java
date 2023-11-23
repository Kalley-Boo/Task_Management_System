package Models;

import Models.Contracts.HistoryLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryLogImpl implements HistoryLog {
    public static final String DESCRIPTION_EMPTY = "Description cannot be empty";
    public static final String TIME_EVENT = "Time: %s \nEvent: %s";
    public static final String FORMATTING = "dd-MM-yyyy HH:mm";
    private final String description;
    private final LocalDateTime timestamp;

    public HistoryLogImpl(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY);
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String viewInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTING);
        String formattedTimestamp = timestamp.format(formatter);
        return String.format(TIME_EVENT, formattedTimestamp, description);
    }

}
