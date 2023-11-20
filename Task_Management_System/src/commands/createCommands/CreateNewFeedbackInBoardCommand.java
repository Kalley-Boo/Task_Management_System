package commands.createCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;

import java.util.ArrayList;
import java.util.List;

public class CreateNewFeedbackInBoardCommand implements Command {

    private final List<String> expectedArguments;

    public static final int EXPECTED_PARAMETERS_COUNT = 3;
    public final static String INVALID_RATING = "Invalid rating. Expected a number.";
    private static final String FEEDBACK_CREATED = "Feedback with title %s was created!";
    private final BoardRepository boardRepository;

    public CreateNewFeedbackInBoardCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a title for the feedback");
        expectedArguments.add("description");
        expectedArguments.add("rating");
    }

    private String createFeedback(String title, String description, int rating){
        boardRepository.createFeedback(title, description, rating);
        return String.format(FEEDBACK_CREATED, title);
    }


    @Override
    public String execute(List<String> parameters) {
        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = Parser.tryParseInt(parameters.get(2), INVALID_RATING);
        return createFeedback(title, description, rating);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
