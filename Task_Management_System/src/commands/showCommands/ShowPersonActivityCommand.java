package commands.showCommands;

import Models.Contracts.Person;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class ShowPersonActivityCommand implements Command {

    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final BoardRepository boardRepository;

    public ShowPersonActivityCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public String showPersonActivity(String name){
        return this.boardRepository.findPersonByName(name).displayHistory();
    }
    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return showPersonActivity(parameters.get(0));
    }
}
