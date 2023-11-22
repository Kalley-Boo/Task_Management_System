package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Printer;
import util.Validator;

import java.util.ArrayList;
import java.util.List;


public class ShowBoardSActivityCommand implements Command {
    private final List<String> expectedArguments;

    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final BoardRepository boardRepository;


    public ShowBoardSActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("the board's title");
    }

    private String showBoardSActivity(String boardName) {
        return Printer.historyPrinter(boardRepository.findBoardByName(boardName).getHistoryLog());
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
