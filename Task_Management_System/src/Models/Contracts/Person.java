package Models.Contracts;

public interface Person {
    public String displayHistory();
    public String getName();
    public void addTask(Task task);
    public void displayTasks();

    String print();
}
