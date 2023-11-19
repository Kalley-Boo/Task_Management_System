package commands;

import Models.Contracts.Story;
import Models.Enums.StatusStory;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class ChangeStoryStatusCommand implements Command {
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "Change story status command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    public static final String STATUS_UPDATED = "Status of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStoryStatusCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String changeStoryStatus(String storyName, String statusStr){
        StatusStory newStatus = StatusStory.valueOf(statusStr.toUpperCase());
        Story story = boardRepository.findStoryByName(storyName);
        story.editStatus(newStatus);
        return String.format(STATUS_UPDATED, storyName, newStatus);
    }
    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }
        String storyName = parameters.get(0);
        String statusStr = parameters.get(1);
        return changeStoryStatus(storyName, statusStr);
    }
}
