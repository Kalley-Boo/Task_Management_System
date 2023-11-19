package commands;

import Models.Contracts.Story;
import Models.Enums.TaskSize;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class ChangeStorySize implements Command {
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "Change story size command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    public static final String SIZE_UPDATED = "Size of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStorySize(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String changeStorySize(String storyName, String sizeStr) {
        TaskSize newSize = TaskSize.valueOf(sizeStr.toUpperCase());
        Story story = boardRepository.findStoryByName(storyName);
        story.editSize(newSize);
        return String.format(SIZE_UPDATED, storyName, newSize);
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String storyName = parameters.get(0);
        String sizeStr = parameters.get(1);
        return changeStorySize(storyName, sizeStr);
    }
}
