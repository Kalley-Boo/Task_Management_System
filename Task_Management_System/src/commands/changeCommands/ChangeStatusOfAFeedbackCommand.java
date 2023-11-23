package commands.changeCommands;

import Models.BoardImpl;
import Models.Contracts.Feedback;
import Models.Contracts.Task;
import Models.Enums.StatusFeedback;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ChangeStatusOfAFeedbackCommand implements Command {
    public static final String TITLE_OF_FEEDBACK = "title of feedback";
    public static final String NEW_STATUS = "new status (new, unscheduled, scheduled, done)";
    public static final String COMMAND_IS_DONE = "The status of bug '%s' changed %s -> %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final List<String> expectedArguments;

    private final BoardRepository boardRepository;

    public ChangeStatusOfAFeedbackCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_OF_FEEDBACK);
        expectedArguments.add(NEW_STATUS);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String feedbackName = parameters.get(0);
        StatusFeedback status = Parser.tryParseEnum(parameters.get(1), StatusFeedback.class);
        return changeStatusOfAFeedback(feedbackName, status);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private String changeStatusOfAFeedback(String feedbackName, StatusFeedback status) {
        Feedback old = boardRepository.findFeedbackByName(feedbackName);
        old.updateStatus(status);
        return String.format(COMMAND_IS_DONE, feedbackName, old.getStatus(), status);
    }
}
