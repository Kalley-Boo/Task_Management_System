package commands.FilteringAndSorting;

import models.contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class FilterTasksByTitleCommand implements Command {
    public static final String TITLE = "Enter sentence (will show all tasks that contain this sentence in their title)";
    public static final String NO_TASKS_FOUND = "No tasks with similar title found";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public FilterTasksByTitleCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE);
    }

    private String filterTasks(String sentence) {
        List<Task> filteredTasks = boardRepository.getTasks()
                        .stream()
                        .filter(task -> task.getTitle().contains(sentence))
                        .toList();
        if (filteredTasks.isEmpty()) {
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : filteredTasks) {
            stringBuilder.append(task.getId()).append(". ");
            stringBuilder.append(task.getTitle()).append("\n");
            stringBuilder.append("Description: ").append(task.getDescription()).append("\n");
        }
        return new String(stringBuilder).trim();
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return filterTasks(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
