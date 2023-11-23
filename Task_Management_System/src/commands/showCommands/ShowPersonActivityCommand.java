package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ShowPersonActivityCommand implements Command {
    public static final String THE_PERSON = "the person's name";
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final BoardRepository boardRepository;

    public ShowPersonActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_PERSON);
    }

    public String showPersonActivity(String name) {
        return this.boardRepository.findPersonByName(name).displayHistory();
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return showPersonActivity(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
