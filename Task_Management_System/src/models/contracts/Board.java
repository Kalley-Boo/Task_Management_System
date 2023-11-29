package models.contracts;

import java.util.List;

public interface Board extends Printable {
    String getName();

    List<HistoryLog> getHistoryLog();

    void addTask(Task task);

}
