package commands.FilteringAndSorting;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import models.contracts.Story;

import javax.print.DocFlavor;
import java.util.Comparator;
import java.util.List;

public class SortStoryByPriority implements Command {
    public static final String NO_TASKS_FOUND = "There are no stories currently";
    private final BoardRepository boardRepository;

    public SortStoryByPriority(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String sortStoryByPriority() {
        List<Story> sortedStories = boardRepository.getStories()
                .stream()
                .sorted(Comparator.comparing(Story::getPriority))
                .toList();
        if (sortedStories.isEmpty()) {
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Story story : sortedStories){
            stringBuilder.append("Story's title: ").append(story.getTitle()).append("\n");
            stringBuilder.append("Story's priority: ").append(story.getPriority()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return sortStoryByPriority();
    }


    @Override
    public List<String> getExpectedArguments() {
        return null;
    }
}
