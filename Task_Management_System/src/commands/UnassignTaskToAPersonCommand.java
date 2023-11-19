package commands;
import Models.BoardImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;


public class UnassignTaskToAPersonCommand implements Command {
    private static final String COMMAND_IS_DONE = "Task with name %s has been unassigned to person with name %s.";
    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "UnassignTaskToAPerson Command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private BoardRepository boardRepository;


    public UnassignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }



    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String personName = parameters.get(0);
        String taskName = parameters.get(1);

        unassignTaskToAPerson(personName, taskName);
        return String.format(COMMAND_IS_DONE, taskName, personName);
    }
    private void unassignTaskToAPerson(String personName, String taskName){
        boardRepository.findPersonByName(personName).removeTask(boardRepository.findTaskByTitle(taskName));
    }
}
