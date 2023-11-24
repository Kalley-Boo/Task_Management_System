package models.contracts;
import java.util.List;

public interface Board extends Printable {
    String getName();
    public List<HistoryLog> getHistoryLog();
    public void addTask(Task task);

}
