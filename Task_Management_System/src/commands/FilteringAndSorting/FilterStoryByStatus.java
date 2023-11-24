package commands.FilteringAndSorting;

import models.contracts.Story;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class FilterStoryByStatus implements Command {
    public static final String TITLE = "Enter status (will show all stories that are with this status)";
    public static final String NO_STORIES = "There are no stories with this status currently.";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public FilterStoryByStatus(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE);
    }

    private String filterStoryByStatus(String status){
        List<Story> filteredStories = boardRepository.getStories()
                .stream()
                .filter(story -> story.getStatus().toString().equalsIgnoreCase(status))
                .toList();
        if(filteredStories.isEmpty()){
            return NO_STORIES;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Story story : filteredStories){
            stringBuilder.append(story.getTitle()).append("\n");
            stringBuilder.append(story.getStatus()).append("\n");
        }
        return new String(stringBuilder);
    }


    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return filterStoryByStatus(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
