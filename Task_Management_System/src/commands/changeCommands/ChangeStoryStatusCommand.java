package commands.changeCommands;

import Models.Contracts.Story;
import Models.Enums.StatusStory;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ChangeStoryStatusCommand implements Command {
    public static final String TITLE_OF_STORY = "title of Story";
    public static final String NEW_STATUS = "new status (not_done, in_progress, done)";
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String STATUS_UPDATED = "Status of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStoryStatusCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_OF_STORY);
        expectedArguments.add(NEW_STATUS);
    }

    public String changeStoryStatus(String storyName, StatusStory status) {
        Story story = boardRepository.findStoryByName(storyName);
        story.editStatus(status);
        return String.format(STATUS_UPDATED, storyName, status);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String storyName = parameters.get(0);
        String statusStr = parameters.get(1);
        StatusStory newStatus = Parser.tryParseEnum(statusStr, StatusStory.class);
        return changeStoryStatus(storyName, newStatus);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
