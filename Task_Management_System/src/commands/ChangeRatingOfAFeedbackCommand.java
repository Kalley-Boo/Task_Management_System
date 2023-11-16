package commands;
import Models.BoardImpl;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class ChangeRatingOfAFeedbackCommand implements Command {
    private static final String COMMAND_IS_DONE = "Feedback with name %s has changed its rating to %d.";
    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "ChangeRatingOfAFeedback Command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private BoardRepository boardRepository;

    public ChangeRatingOfAFeedbackCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }



    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT){
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String feedbackName = parameters.get(0);
        int rating = Integer.parseInt(parameters.get(1));

        changeRatingOfAFeedback(feedbackName, rating);
        return String.format(COMMAND_IS_DONE, feedbackName, rating);
    }
    private void changeRatingOfAFeedback(String feedbackName, int rating){
        boardRepository.changeRatingOfAFeedback(feedbackName, rating);
    }
}
