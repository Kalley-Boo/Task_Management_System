package commands.changeCommands;

import Models.BoardImpl;
import Models.Enums.StatusFeedback;
import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Parser;
import util.Validator;

import java.util.List;

public class ChangeStatusOfAFeedbackCommand implements Command {
    public static final String COMMAND_IS_DONE = "Feedback with title %s has changed its status to %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;

    private final BoardRepository boardRepository;

    public ChangeStatusOfAFeedbackCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String feedbackName = parameters.get(0);
        StatusFeedback status = Parser.tryParseEnum(parameters.get(1), StatusFeedback.class);
        return changeStatusOfAFeedback(feedbackName, status);
    }

    private String changeStatusOfAFeedback(String feedbackName, StatusFeedback status) {
        boardRepository.findFeedbackByName(feedbackName).setStatus(status);
        return String.format(COMMAND_IS_DONE, feedbackName, status);

    }
}