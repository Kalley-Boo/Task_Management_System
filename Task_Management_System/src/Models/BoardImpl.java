package Models;

import Models.Contracts.Board;
import Models.Contracts.Task;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 10;


    private String name;
    private final List<Task> tasks;
    private final List<HistoryLog> historyLog;

    public BoardImpl(String name){
        tasks = new ArrayList<>();
        historyLog = new ArrayList<>();
        setName(name);
    }

    private void setName(String name) {
        Validator.validateIntRange(name.length(), NAME_MIN_LENGTH, NAME_MAX_LENGTH);
        this.name = name;
    }

    @Override
    public List<Task> getTasks() {
        return this.getTasks();
    }

    @Override
    public List<HistoryLog> getHistoryLogs() {
        return this.getHistoryLogs();
    }
}
