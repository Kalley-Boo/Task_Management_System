package commands.FilteringAndSorting;

import models.contracts.Story;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;


public class ListStoriesWithAssignee implements Command {

    private BoardRepository boardRepository;
    public static final String NO_TASKS_FOUND = "No stories with assignee were found";

    public ListStoriesWithAssignee(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String listStoriesWithAssignee() {
        List<Story> storiesWithAssignee = new ArrayList<>();
        for (Story story : boardRepository.getStories()
        ) {
            if (story.getAssignee() != null) {
                storiesWithAssignee.add(story);
            }
        }
        if (storiesWithAssignee.isEmpty()) {
            return NO_TASKS_FOUND;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Story story : storiesWithAssignee) {
                stringBuilder.append("Name of the story: ");
                stringBuilder.append(story.getTitle()).append("\n");
                stringBuilder.append("Name of the assignee: ");
                stringBuilder.append(story.getAssignee()).append("\n");
            }
            return new String(stringBuilder).trim();
        }
    }

    @Override
    public String execute(List<String> parameters) {
        return listStoriesWithAssignee();
    }

    @Override
    public List<String> getExpectedArguments() {
        return new ArrayList<>();
    }
}
