package Models;

import Models.Contracts.Board;
import Models.Contracts.HistoryLog;
import Models.Contracts.Task;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 10;
    private static final String BOARD_HEADER = "---Board---";
    private static final String NO_TASKS_ON_THIS_BOARD = "There are no tasks on this board.";
    private static final String TASKS_HEADER = "---Tasks---";
    private String name;
    private final List<Task> tasks;
    private final List<HistoryLogImpl> historyLog;

    public BoardImpl(String name){
        tasks = new ArrayList<>();
        historyLog = new ArrayList<>();
        setName(name);
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BOARD_HEADER).append("\n");
        stringBuilder.append(this.name).append("\n");
        if (this.tasks.isEmpty()){
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

    private void setName(String name) {
        Validator.validateIntRange(
                name.length(),
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public List<HistoryLogImpl> getHistoryLog() {
        return historyLog;
    }
}
