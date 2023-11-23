package commands.createCommands;

import Models.Contracts.Bug;
import Models.Contracts.Feedback;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateNewFeedbackInBoardCommand implements Command {

    public static final String TITLE_FOR_THE_FEEDBACK = "a title for the feedback (10-100 characters)";
    public static final String DESCRIPTION = "description (10-500 characters)";
    public static final String RATING_1_10 = "rating (1-10)";
    private final List<String> expectedArguments;

    public static final int EXPECTED_PARAMETERS_COUNT = 3;
    public final static String INVALID_RATING = "Invalid rating. Expected a number.";
    private static final String FEEDBACK_CREATED = "Feedback with title %s was created!";
    public static final String INVALID_TITLE_LENGTH = "The length of the title must be 10-100";
    public static final String FEEDBACK_EXISTS = "Feedback with this name already exists.";
    public static final String INVALID_DESCRIPTION_LENGTH = "The length of the description must be 10-500";
    private final BoardRepository boardRepository;

    public CreateNewFeedbackInBoardCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_FOR_THE_FEEDBACK);
        expectedArguments.add(DESCRIPTION);
        expectedArguments.add(RATING_1_10);
    }

    private boolean feedbackExists(String title) {
        for (Feedback feedback : boardRepository.getFeedbacks()) {//TODO stream
            if (feedback.getName().equals(title)) {
                return true;
            }
        }
        return false;
    }

    private String createFeedback(String title, String description, int rating) {
        boardRepository.createFeedback(title, description, rating);
        return String.format(FEEDBACK_CREATED, title);
    }


    @Override
    public String execute(List<String> parameters) {
        String title = parameters.get(0);
        if (feedbackExists(title)) {
            throw new InvalidInputException(FEEDBACK_EXISTS);
        }
        Validator.validateStringLength(title, 10, 100, INVALID_TITLE_LENGTH);
        String description = parameters.get(1);
        Validator.validateStringLength(description, 10, 500, INVALID_DESCRIPTION_LENGTH);
        int rating = Parser.tryParseInt(parameters.get(2), INVALID_RATING);
        return createFeedback(title, description, rating);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
