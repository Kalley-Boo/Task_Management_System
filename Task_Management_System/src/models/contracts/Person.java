package models.contracts;

public interface Person {
    public String displayHistory();
    public String getName();
    public void addTask(Task task);
    public void displayTasks();
    public void removeTask(Task task);
    String print();
}
