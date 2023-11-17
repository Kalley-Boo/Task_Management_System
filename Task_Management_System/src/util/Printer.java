package util;

import Models.Contracts.HistoryLog;

import java.util.List;

public abstract class Printer {

    public static String historyPrinter(List<HistoryLog> historyLogs){
        StringBuilder stringBuilder = new StringBuilder();
        for (HistoryLog log : historyLogs) {
            stringBuilder.append(log.viewInfo()).append("\n");
        }
        return new String(stringBuilder).trim();
    }
}
