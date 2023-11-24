package commands.FilteringAndSorting;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import models.contracts.Feedback;

import java.util.Comparator;
import java.util.List;

public class SortFeedbackByTitle implements Command {
    public static final String NO_TASKS_FOUND = "There are no feedbacks currently";
    private final BoardRepository boardRepository;

    public SortFeedbackByTitle(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    private String sortFeedbackByTitle(){
        List<Feedback> sortedFeedbacks = boardRepository.getFeedbacks()
                .stream()
                .sorted(Comparator.comparing(Feedback::getTitle))
                .toList();
        if(sortedFeedbacks.isEmpty()){
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Feedback feedback : sortedFeedbacks){
            stringBuilder.append("Feedback's title: ").append(feedback.getTitle()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return sortFeedbackByTitle();
    }

    @Override
    public List<String> getExpectedArguments() {
        return null;
    }
}
