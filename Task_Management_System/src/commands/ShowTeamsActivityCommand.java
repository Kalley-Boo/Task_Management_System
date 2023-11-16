package commands;

import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class ShowTeamsActivityCommand implements Command {

    private static final int EXPECTED_PARAM_COUNT = 1;
    private static final String INVALID_PARAM_COUNT = String.format(
            "The show teams activity command expects %d parameters.",
            EXPECTED_PARAM_COUNT);
    private final BoardRepository boardRepository;

    public ShowTeamsActivityCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public String showTeamsActivity(String name){
    //TODO figure out what I need to print exactly
        return null;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAM_COUNT){
            throw new IllegalArgumentException(INVALID_PARAM_COUNT);
        }
        return showTeamsActivity(parameters.get(0));
    }
}
