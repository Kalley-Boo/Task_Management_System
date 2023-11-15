package commands;

import Models.BoardImpl;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class CreateBoardCommand implements Command {

    private static final String BOARD_CREATED = "Board with name %s was created!";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "CreateBoard command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);


    private final BoardRepository boardRepository;

    public CreateBoardCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

    }
    private String createBoard(String name){
        boardRepository.createBoard(name);
        return String.format(BOARD_CREATED, name);
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String name = parameters.get(0);
        return createBoard(name);
    }
}
