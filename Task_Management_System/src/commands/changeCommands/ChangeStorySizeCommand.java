package commands.changeCommands;

import Models.Contracts.Story;
import Models.Enums.TaskSize;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ChangeStorySizeCommand implements Command {
    public static final String TITLE_OF_STORY = "title of Story";
    public static final String NEW_SIZE = "new size (small, medium, large)";
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String SIZE_UPDATED = "Size of %s updated to %s.";
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public ChangeStorySizeCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_OF_STORY);
        expectedArguments.add(NEW_SIZE);
    }

    private String changeStorySize(String storyName, TaskSize size) {
        Story story = boardRepository.findStoryByName(storyName);
        story.editSize(size);
        return String.format(SIZE_UPDATED, storyName, size);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String storyName = parameters.get(0);
        String sizeStr = parameters.get(1);
        TaskSize newSize = Parser.tryParseEnum(sizeStr, TaskSize.class);
        return changeStorySize(storyName, newSize);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
