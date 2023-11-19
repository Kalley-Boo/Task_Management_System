package util;

import Models.Contracts.HistoryLog;
import Models.HistoryLogImpl;

import java.util.List;

public abstract class Printer {

    private final static String ACTIVITY_BANNER = "---ACTIVITY---";

    public static String historyPrinter(List<HistoryLogImpl> historyLogs){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ACTIVITY_BANNER).append("\n");
        for (HistoryLog log : historyLogs) {
            stringBuilder.append(log.viewInfo()).append("\n");
        }
        stringBuilder.append(ACTIVITY_BANNER);
        return new String(stringBuilder);
    }
}
