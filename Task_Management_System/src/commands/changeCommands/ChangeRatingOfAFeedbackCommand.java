package commands.changeCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.List;

public class ChangeRatingOfAFeedbackCommand implements Command {
    public static final String COMMAND_IS_DONE = "Feedback with title %s has changed its rating to %d.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String INVALID_RATING = "Invalid value for rating. Should be a number.";

    private final BoardRepository boardRepository;

    public ChangeRatingOfAFeedbackCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    private String changeRatingOfAFeedback(String feedbackName, int rating) {
        boardRepository.findFeedbackByName(feedbackName).setRating(rating);
        return String.format(COMMAND_IS_DONE, feedbackName, rating);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String feedbackName = parameters.get(0);
        int rating = Parser.tryParseInt(parameters.get(1), INVALID_RATING);
        return changeRatingOfAFeedback(feedbackName, rating);
    }
}
