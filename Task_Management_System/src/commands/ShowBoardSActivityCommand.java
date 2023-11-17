package commands;
import Models.BoardImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;


public class ShowBoardSActivityCommand implements Command{
    private static final String COMMAND_IS_DONE = "These are all activities for board with name %s.";
    public static final int EXPECTED_PARAMETERS_COUNT = 1;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "ShowBoardSActivity command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private BoardRepository boardRepository;


    public ShowBoardSActivityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
        throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
    }

        String boardName = parameters.get(0);
        showBoardSActivity(boardName);
        return String.format(COMMAND_IS_DONE, boardName);
    }
    private void showBoardSActivity(String boardName){
        boardRepository.showBoardsActivity(boardName);
    }
}
