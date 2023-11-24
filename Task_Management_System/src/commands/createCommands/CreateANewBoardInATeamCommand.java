package commands.createCommands;

import models.contracts.Board;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateANewBoardInATeamCommand implements Command {
    public static final String TITLE_5_10_CHARACTERS = "a title for the board (5-10 characters)";
    public static final String A_TEAM = "a team to which it will be assigned";
    public static final String BOARD_CREATED = "Board with name %s was created in team with name %s!";
    public static final String INVALID_TITLE_LENGTH = "The length of the title must be 5-10";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String BOARD_EXISTS = "Board with the same name already exists in this team.";
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public CreateANewBoardInATeamCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_5_10_CHARACTERS);
        expectedArguments.add(A_TEAM);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String boardName = parameters.get(0);
        Validator.validateStringLength(boardName, 5, 10, INVALID_TITLE_LENGTH);
        String teamName = parameters.get(1);
        if (boardExists(boardName, teamName)) {
            throw new InvalidInputException(BOARD_EXISTS);
        }
        return createANewBoardInATeam(boardName, teamName);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private boolean boardExists(String boardName, String teamName) {
        return boardRepository.findTeamByName(teamName).getBoards().stream().anyMatch(board -> board.getName().equals(boardName));
    }

    private String createANewBoardInATeam(String boardName, String teamName) {
        Board board1 = boardRepository.createBoard(boardName);
        boardRepository.findTeamByName(teamName).addBoard(board1);
        return String.format(BOARD_CREATED, boardName, teamName);
    }
}
