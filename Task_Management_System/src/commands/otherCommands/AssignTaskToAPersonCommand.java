package commands.otherCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskToAPersonCommand implements Command {
    private final List<String> expectedArguments;
    public static final String COMMAND_IS_DONE = "Task with name %s has been assigned to person with name %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final BoardRepository boardRepository;


    public AssignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("the new assignee's name");
        expectedArguments.add("the task's title");
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String personName = parameters.get(0);
        String task = parameters.get(1);
        return assignTaskToAPerson(personName, task);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private String assignTaskToAPerson(String personName, String task) {
        boardRepository.findPersonByName(personName).addTask(boardRepository.findTaskByTitle(task));
        return String.format(COMMAND_IS_DONE, task, personName);
        //TODO the assignee is not being added to the task (Assigneable interface)
    }
}
