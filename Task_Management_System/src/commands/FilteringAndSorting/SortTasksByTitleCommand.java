package commands.FilteringAndSorting;

import models.contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.Comparator;
import java.util.List;

public class SortTasksByTitleCommand implements Command {
    public static final String NO_TASKS_FOUND = "There are no tasks currently";
    private final BoardRepository boardRepository;

    public SortTasksByTitleCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String sortTasksByTitle() {
        List<Task> sortedTasks = boardRepository.getTasks().stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
        if (sortedTasks.isEmpty()) {
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : sortedTasks) {
            stringBuilder.append(task.getId()).append(". ");
            stringBuilder.append(task.getTitle()).append("\n");
            stringBuilder.append("Description: ").append(task.getDescription()).append("\n");
        }
        return new String(stringBuilder).trim();

    }

    @Override
    public String execute(List<String> parameters) {
        return sortTasksByTitle();
    }

    @Override
    public List<String> getExpectedArguments() {
        return null;
    }
}
