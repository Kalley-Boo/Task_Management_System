package commands.showCommands;

import Models.Contracts.Bug;
import Models.Contracts.Story;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;


public class ListStoriesWithAssigneeCommand {

    private BoardRepository boardRepository;
    public static final String NO_TASKS_FOUND = "No stories with assignee were found";


    public ListStoriesWithAssigneeCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String ListStoriesWithAssignee() {
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

}
