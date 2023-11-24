package commands.showCommands;

import Models.Contracts.Bug;
import Models.Contracts.Story;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterStoryByAssigneeCommand implements Command {
    private final BoardRepository boardRepository;
    private final List<String> expectedArguments;
    public static final String TITLE = "Enter assignee (will show all stories that contain this assignee.)";
    public static final String NO_TASKS_FOUND = "No stories with this assignee were found";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;

    public FilterStoryByAssigneeCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE);
    }

    private String filterStories(String sentence) {
        List<Story> filteredTasks = boardRepository.getStories().stream().filter(story -> story.getAssignee().getName().equals(sentence)).collect(Collectors.toList());
        if (filteredTasks.isEmpty()) {
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Story story : filteredTasks) {
            stringBuilder.append(story.getTitle()).append("\n");
        }
        return new String(stringBuilder).trim();
    }
    @Override
    public String execute(List<String> parameters) {
        return null;
    }

    @Override
    public List<String> getExpectedArguments() {
        return null;
    }

}
