package Models;

import Models.Contracts.Person;
import Models.Contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    public static final int NAME_MIN_LEN = 5;
    public static final int NAME_MAX_LEN = 15;
    public static final String NAME_NOT_VALID = "Please enter a valid name with 5 to 15 symbols";
    public static final String TASK_CANNOT_BE_NULL = "Task cannot be null";
    private String name;
    private List<Task> tasks;
    private final List<HistoryLog> history;

    public PersonImpl(String name){
        this.tasks = new ArrayList<>();
        this.history = new ArrayList<>();
        setName(name);
    }//constructor

    //-----------------------------------------methods-----------------------------------------

    public void displayHistory() {
        for (HistoryLog log : history) {
            System.out.println(log.viewInfo());
        }
    }

    protected void logEvent(String event) {
        history.add(new HistoryLog(event));
    }

    private void validateName(String name){
        if(name.length() < NAME_MIN_LEN || name.length() > NAME_MAX_LEN){
            throw new IllegalArgumentException(NAME_NOT_VALID);
        }
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException(TASK_CANNOT_BE_NULL);
        }
        tasks.add(task);
        logEvent(String.format("Task added: %s", task.getDescription()));
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available for " + name);
        } else {
            System.out.println("Tasks for " + name + ":");
            for (Task task : tasks) {
                System.out.println(task.getDescription());
            }
        }
    }

    //-----------------------------------setters and getters------------------------------------
    private void setName(String name){
        validateName(name);
        logEvent(String.format("Name set to: %s", name));
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
