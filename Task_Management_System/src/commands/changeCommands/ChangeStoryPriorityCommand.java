package commands.changeCommands;

import Models.Contracts.Story;
import Models.Enums.Priority;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ChangeStoryPriorityCommand implements Command {
    public static final String TITLE_OF_STORY = "title of Story";
    public static final String NEW_PRIORITY = "new priority (low, medium, high)";
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String PRIORITY_UPDATED = "Priority of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStoryPriorityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_OF_STORY);
        expectedArguments.add(NEW_PRIORITY);
    }

    public String changeStoryPriority(String storyName, Priority priority) {
        Story story = boardRepository.findStoryByName(storyName);
        story.editPriority(priority);
        return String.format(PRIORITY_UPDATED, storyName, priority);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String storyName = parameters.get(0);
        String priorityStr = parameters.get(1);
        Priority newPriority = Parser.tryParseEnum(priorityStr, Priority.class);
        return changeStoryPriority(storyName, newPriority);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
