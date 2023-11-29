package commands.FilteringAndSorting;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import models.contracts.Story;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class FilterStoryByAssignee implements Command {
    private final BoardRepository boardRepository;
    private final List<String> expectedArguments;
    public static final String TITLE = "Enter assignee (will show all stories that contain this assignee.)";
    public static final String NO_TASKS_FOUND = "No stories with this assignee were found";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;

    public FilterStoryByAssignee(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE);
    }

    private String filterStoryByAssignee(String assignee){
        List<Story> filteredStories = boardRepository.getStories()
                .stream()
                .filter(story -> story.getAssignee().getName().equalsIgnoreCase(assignee))
                .toList();
        if(filteredStories.isEmpty()){
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Story story : filteredStories){
            stringBuilder.append("Story's name: ").append(story.getTitle()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return filterStoryByAssignee(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
