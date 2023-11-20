package commands.createCommands;

import Models.BoardImpl;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardCommand implements Command {
    private final List<String> expectedArguments;
    private static final String BOARD_CREATED = "Board with name %s was created!";

    public static final int EXPECTED_PARAMETERS_COUNT = 1;


    private final BoardRepository boardRepository;

    public CreateBoardCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a name for the board");
    }
    private String createBoard(String name){
        boardRepository.createBoard(name);
        return String.format(BOARD_CREATED, name);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String name = parameters.get(0);
        return createBoard(name);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
