package Models.Contracts;

import Models.HistoryLog;

import java.util.List;

public interface Board {

    String getName();
    List<Task> getTasks();
    List<HistoryLog> getHistoryLogs();
}
