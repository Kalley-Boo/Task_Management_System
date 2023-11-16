package commands;

import Models.Contracts.Person;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class ShowPersonActivityCommand implements Command {

    private static final int EXPECTED_PARAMETERS_COUNT = 1;

    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "ShowPersonActivity command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private final BoardRepository boardRepository;

    public ShowPersonActivityCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public String showPersonActivity(String name){
        Person foundPerson = this.boardRepository.findPersonByName(name);
        return foundPerson.displayHistory();
    }
    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        return showPersonActivity(parameters.get(0));
    }
}
