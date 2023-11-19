package commands;

import Models.BoardImpl;
import Models.Contracts.Board;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {
    private static final String COMMAND_START = "These are all boards for team with name %s.";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "ShowAllTeamBoards command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    public static final String ALL_BOARDS_BANNER = "---BOARDS---";
    private final BoardRepository boardRepository;


    public ShowAllTeamBoardsCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String showAllTeamBoards(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(COMMAND_START, name));
        stringBuilder.append(ALL_BOARDS_BANNER).append("%n");
        for (Board board : boardRepository.findTeamByName(name).getBoards()) {
            stringBuilder.append(board.print()).append("%n");
        }
        stringBuilder.append(ALL_BOARDS_BANNER);
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new IllegalArgumentException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        return showAllTeamBoards(parameters.get(0));

    }

}