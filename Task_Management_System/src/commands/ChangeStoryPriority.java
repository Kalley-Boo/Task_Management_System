package commands;

import Models.Contracts.Story;
import Models.Enums.Priority;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class ChangeStoryPriority implements Command {
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "Change story priority command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    public static final String PRIORITY_UPDATED = "Priority of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStoryPriority(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String changeStoryPriority(String storyName, String priorityStr){
        Priority newPriority = Priority.valueOf(priorityStr.toUpperCase());
        Story story = boardRepository.findStoryByName(storyName);
        story.editPriority(newPriority);
        return String.format(PRIORITY_UPDATED, storyName, newPriority);
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String storyName = parameters.get(0);
        String priorityStr = parameters.get(1);
        return changeStoryPriority(storyName, priorityStr);
    }
}
