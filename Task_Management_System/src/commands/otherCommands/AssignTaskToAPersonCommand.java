package commands.otherCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class AssignTaskToAPersonCommand implements Command {
    public static final String COMMAND_IS_DONE = "Task with name %s has been assigned to person with name %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final BoardRepository boardRepository;


    public AssignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String personName = parameters.get(0);
        String task = parameters.get(1);
        return assignTaskToAPerson(personName, task);
    }

    private String assignTaskToAPerson(String personName, String task) {
        boardRepository.findPersonByName(personName).addTask(boardRepository.findTaskByTitle(task));
        return String.format(COMMAND_IS_DONE, task, personName);
    }
}
