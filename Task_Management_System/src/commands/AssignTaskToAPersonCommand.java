package commands;
import Models.BoardImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class AssignTaskToAPersonCommand implements Command {
    public static final String COMMAND_IS_DONE = "Task with name %s has been assigned to person with name %s.";
    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "AssignTaskToAPerson Command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private BoardRepository boardRepository;


    public AssignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String personName = parameters.get(0);
        String task = parameters.get(1);

        assignTaskToAPerson(personName, task);
            return String.format(COMMAND_IS_DONE, task, personName);
        }
        private void assignTaskToAPerson(String personName, String task){
            boardRepository.findPersonByName(personName).addTask(boardRepository.findTaskByTitle(task));
        }
}
