package Models.Contracts;

import Models.HistoryLogImpl;

import java.util.List;

public interface Board extends Printable{

    String getName();
    List<Task> getTasks();
    List<HistoryLogImpl> getHistoryLogs();
}
