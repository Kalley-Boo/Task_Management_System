package commands;

import Models.BoardImpl;
import Models.Enums.StatusFeedback;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;
public class ChangeStatusOfAFeedback implements Command {
    private static final String COMMAND_IS_DONE = "Feedback with name %s has changed its status to %s.";
    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "ChangeStatusOfAFeedback Command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private BoardRepository boardRepository;

    public ChangeStatusOfAFeedback(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }



    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String feedbackName = parameters.get(0);
        StatusFeedback status = StatusFeedback.valueOf(parameters.get(1));

        changeStatusOfAFeedback(feedbackName, status);
        return String.format(COMMAND_IS_DONE, feedbackName, status);
    }

    private void changeStatusOfAFeedback(String feedbackName, StatusFeedback status){
        boardRepository.findFeedbackByName(feedbackName).setStatus(status);
    }
}
