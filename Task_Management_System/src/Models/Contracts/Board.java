package Models.Contracts;
import Models.HistoryLogImpl;
import java.util.List;

public interface Board extends Printable {
    String getName();
    public List<HistoryLogImpl> getHistoryLog();
    public void addTask(Task task);

}
