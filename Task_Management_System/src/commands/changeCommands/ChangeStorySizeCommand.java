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
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String SIZE_UPDATED = "Size of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStorySizeCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("title of Story");
        expectedArguments.add("new size (small, medium, large)");
    }

    public String changeStorySize(String storyName, String sizeStr) {
        TaskSize newSize = Parser.tryParseEnum(sizeStr, TaskSize.class);
        Story story = boardRepository.findStoryByName(storyName);
        story.editSize(newSize);
        return String.format(SIZE_UPDATED, storyName, newSize);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String storyName = parameters.get(0);
        String sizeStr = parameters.get(1);
        return changeStorySize(storyName, sizeStr);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
