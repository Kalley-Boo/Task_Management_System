package util;

import models.contracts.HistoryLog;

import java.util.List;

public abstract class Printer {
    private final static String ACTIVITY_BANNER = "---ACTIVITY---";

    public static String historyPrinter(List<HistoryLog> historyLogs) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ACTIVITY_BANNER).append("\n");
        for (HistoryLog log : historyLogs) {
            stringBuilder.append(log.viewInfo()).append("\n");
        }
        stringBuilder.append(ACTIVITY_BANNER);
        return new String(stringBuilder);
    }
}
