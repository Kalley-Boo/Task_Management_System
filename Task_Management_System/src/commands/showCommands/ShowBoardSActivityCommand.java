package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Printer;
import util.Validator;

import java.util.ArrayList;
import java.util.List;


public class ShowBoardSActivityCommand implements Command {
    public static final String THE_HISTORY_LOG_IS_EMPTY = "The history log is empty";
    public static final String THE_BOARD_S_TITLE = "the board's title";
    private final List<String> expectedArguments;

    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final BoardRepository boardRepository;


    public ShowBoardSActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_BOARD_S_TITLE);
    }

    private String showBoardSActivity(String boardName) {
        if (boardRepository.findBoardByTitle(boardName).getHistoryLog().isEmpty()) {
            return THE_HISTORY_LOG_IS_EMPTY;
        }
        return Printer.historyPrinter(boardRepository.findBoardByTitle(boardName).getHistoryLog());
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String boardName = parameters.get(0);
        return showBoardSActivity(boardName);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }


}
