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
    public static final String ADDED = "Task added: %s";
    public static final String REMOVED = "Task removed: %s";
    public static final String NO_TASKS = "No tasks available for ";
    public static final String NAME_SET = "Name set to: %s";
    public static final String TASKS_FOR = "Tasks for ";
    public static final String HAS_TASKS = " has tasks: ";
    public static final String HAS_NO_TASKS_ASSIGNED_YET = " - has no tasks assigned yet.";
    private String name;
    private final List<Task> tasks;
    private final List<HistoryLogImpl> history;

    public PersonImpl(String name){
        this.tasks = new ArrayList<>();
        this.history = new ArrayList<>();
        setName(name);
    }//constructor

    //-----------------------------------------methods-----------------------------------------

    public String displayHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HistoryLogImpl log : history) {
            stringBuilder.append(log.viewInfo());
        }
        return new String(stringBuilder);
    }

    protected void logEvent(String event) {
        history.add(new HistoryLogImpl(event));
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
        logEvent(String.format(ADDED, task.getDescription()));
    }
    public void removeTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException(TASK_CANNOT_BE_NULL);
        }
        tasks.remove(task);
        logEvent(String.format(REMOVED, task.getDescription()));
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println(NO_TASKS + name);
        } else {
            System.out.println(TASKS_FOR + name + ":");
            for (Task task : tasks) {
                System.out.println(task.getDescription());
            }
        }
    }

    public String print(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---Member---").append("\n");
        stringBuilder.append(this.name);
        if (this.tasks.isEmpty()){
            stringBuilder.append(HAS_NO_TASKS_ASSIGNED_YET);
        }
        stringBuilder.append(HAS_TASKS);
        for(Task task : this.tasks){
            stringBuilder.append(task.print());
            stringBuilder.append(" ");
        }
        return new String(stringBuilder);
    }

    //-----------------------------------setters and getters------------------------------------
    private void setName(String name){
        validateName(name);
        logEvent(String.format(NAME_SET, name));
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
