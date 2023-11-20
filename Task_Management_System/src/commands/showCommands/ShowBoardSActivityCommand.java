package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Printer;
import util.Validator;

import java.util.List;


public class ShowBoardSActivityCommand implements Command {

    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final BoardRepository boardRepository;


    public ShowBoardSActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String showBoardSActivity(String boardName) {
        return boardRepository.findBoardByName(boardName).print();
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String boardName = parameters.get(0);
        return showBoardSActivity(boardName);
    }


}
