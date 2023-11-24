package commands.showCommands;

import Models.Contracts.Bug;
import Models.Contracts.Person;
import Models.Contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBugByAssigneeCommand implements Command {

    private final BoardRepository boardRepository;
    private final List<String> expectedArguments;
    public static final String TITLE = "Enter assignee (will show all tasks that contain this assignee.)";
    public static final String NO_TASKS_FOUND = "No tasks with this assignee were found";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;

    public FilterBugByAssigneeCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE);
    }
    private String filterTasks(String sentence) {
        List<Bug> filteredTasks = boardRepository.getBugs().stream().filter(bug -> bug.getAssignee().getName().equals(sentence)).collect(Collectors.toList());
        if (filteredTasks.isEmpty()) {
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Bug bug : filteredTasks) {
            stringBuilder.append(bug.getTitle()).append("\n");
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

