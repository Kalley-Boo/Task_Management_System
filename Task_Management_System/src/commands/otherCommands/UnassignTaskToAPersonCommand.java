package commands.otherCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;


public class UnassignTaskToAPersonCommand implements Command {
    private final List<String> expectedArguments;
    public static final String COMMAND_IS_DONE = "Task with name %s has been unassigned to person with name %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final BoardRepository boardRepository;

    public UnassignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add("the name of the assignee");
        expectedArguments.add("the title of the task");
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String personName = parameters.get(0);
        String taskName = parameters.get(1);
        return unassignTaskToAPerson(personName, taskName);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private String unassignTaskToAPerson(String personName, String taskName) {
        boardRepository.findPersonByName(personName).removeTask(boardRepository.findTaskByTitle(taskName));
        return String.format(COMMAND_IS_DONE, taskName, personName);
    }
}
