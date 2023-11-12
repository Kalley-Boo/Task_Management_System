package Models.Contracts;

import Models.HistoryLog;

import java.util.List;

public interface Board {

    List<Task> getTasks();
    List<HistoryLog> getHistoryLogs();
}
