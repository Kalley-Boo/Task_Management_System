package models;

import models.contracts.Board;
import models.contracts.HistoryLog;
import models.contracts.Task;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 10;
    public static final String BOARD_HEADER = "---Board---";
    public static final String NO_TASKS_ON_THIS_BOARD = "There are no tasks on this board.";
    public static final String TASKS_HEADER = "---Tasks---";
    public static final String TASK_ADDED_TO_BOARD = "Task with title %s added to board";
    public static final String BOARD_CREATED = "Board with name %s was created";
    private String name;
    private final List<Task> tasks;
    private final List<HistoryLog> historyLog;

    public BoardImpl(String name) {
        tasks = new ArrayList<>();
        historyLog = new ArrayList<>();
        setName(name);
        historyLog.add(new HistoryLogImpl(String.format(BOARD_CREATED, name)));
    }


    private void setName(String name) {
        Validator.validateIntRange(
                name.length(),
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH);
        this.name = name;
    }

    @Override
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
        historyLog.add(new HistoryLogImpl(String.format(TASK_ADDED_TO_BOARD, task.getTitle())));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<HistoryLog> getHistoryLog() {
        return historyLog;
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BOARD_HEADER).append("\n");
        stringBuilder.append(this.name).append("\n");

        if (this.tasks.isEmpty()) {
            return new String(stringBuilder.append(NO_TASKS_ON_THIS_BOARD));
        }
        stringBuilder.append(TASKS_HEADER).append("\n");

        for (Task task : this.tasks) {
            stringBuilder.append(task.print());
            stringBuilder.append("\n");
        }
        stringBuilder.append(TASKS_HEADER);
        return new String(stringBuilder);
    }
}
