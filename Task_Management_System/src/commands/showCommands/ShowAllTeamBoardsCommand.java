package commands.showCommands;

import Models.BoardImpl;
import Models.Contracts.Board;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {
    public static final String COMMAND_START = "These are all boards for team with name %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    public static final String ALL_BOARDS_BANNER = "---BOARDS---";
    private final BoardRepository boardRepository;


    public ShowAllTeamBoardsCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String showAllTeamBoards(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(COMMAND_START, name));
        stringBuilder.append("\n");
        stringBuilder.append(ALL_BOARDS_BANNER).append("\n");
        for (Board board : boardRepository.findTeamByName(name).getBoards()) {
            stringBuilder.append(board.print()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return showAllTeamBoards(parameters.get(0));

    }

}