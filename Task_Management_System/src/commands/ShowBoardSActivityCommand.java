package commands;

import Models.BoardImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Printer;

import java.util.List;


public class ShowBoardSActivityCommand implements Command {
    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "ShowBoardSActivity command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private final BoardRepository boardRepository;


    public ShowBoardSActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    private String showBoardSActivity(String boardName) {
        return Printer.historyPrinter(boardRepository.findBoardByName(boardName).getHistoryLogs());
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }

        String boardName = parameters.get(0);
        return showBoardSActivity(boardName);
    }


}
