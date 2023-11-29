package commands.FilteringAndSorting;

import models.contracts.Feedback;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class FilterFeedbackByStatus implements Command {
    public static final String TITLE = "Enter status (will show all feedbacks that are with this status)";
    public static final String NO_FEEDBACKS = "There are no feedbacks with this status currently.";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public FilterFeedbackByStatus( BoardRepository boardRepository) {
        this.expectedArguments = new ArrayList<>();
        this.boardRepository = boardRepository;
        expectedArguments.add(TITLE);

    }

    private String filterFeedbackByStatus(String status){
        List<Feedback> filteredFeedbacks = boardRepository.getFeedbacks()
                .stream()
                .filter(feedback -> feedback.getStatus().toString().equalsIgnoreCase(status))
                .toList();
        if(filteredFeedbacks.isEmpty()){
            return NO_FEEDBACKS;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Feedback feedback : filteredFeedbacks){
            stringBuilder.append(feedback.getTitle()).append("\n");
            stringBuilder.append(feedback.getStatus()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return filterFeedbackByStatus(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
