package models.contracts;

public interface Person {
    String displayHistory();

    String getName();

    void addTask(Task task);

    void displayTasks();

    void removeTask(Task task);

    String print();
}
