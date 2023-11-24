package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class FilterBugsbyStatus implements Command {
    public static final String TITLE = "Enter status (will show all bugs that are with this status)";
    public static final String NO_BUGS = "There are no bugs currently.";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final BoardRepository boardRepository;

    public FilterBugsbyStatus(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String sortBugsByStatus(String status){

    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return sortBugsByStatus(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments();
    }
}
