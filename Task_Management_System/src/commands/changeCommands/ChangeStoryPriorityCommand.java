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
    private final List<String> expectedArguments;
    private static final int EXPECTED_PARAMETERS_COUNT = 2;
    public static final String PRIORITY_UPDATED = "Priority of %s updated to %s.";
    private final BoardRepository boardRepository;

    public ChangeStoryPriorityCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("title");
        expectedArguments.add("new priority");
    }

    public String changeStoryPriority(String storyName, String priorityStr){
        Priority newPriority = Parser.tryParseEnum(priorityStr, Priority.class);
        Story story = boardRepository.findStoryByName(storyName);
        story.editPriority(newPriority);
        return String.format(PRIORITY_UPDATED, storyName, newPriority);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String storyName = parameters.get(0);
        String priorityStr = parameters.get(1);
        return changeStoryPriority(storyName, priorityStr);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
