package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateANewBoardInATeamCommand implements Command {
    private final List<String> expectedArguments;
    public static final String BOARD_CREATED = "Board with name %s was created in team with name%s!";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final BoardRepository boardRepository;


    public CreateANewBoardInATeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a title for the board");
        expectedArguments.add("a team to which it will be assigned");
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        return createANewBoardInATeam(boardName, teamName);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private String createANewBoardInATeam(String boardName, String teamName) {
        boardRepository.createANewBoardInATeam(boardName, teamName);
        return String.format(BOARD_CREATED, boardName, teamName);
    }
}
