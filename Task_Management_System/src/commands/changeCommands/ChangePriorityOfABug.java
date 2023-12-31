package commands.changeCommands;

import models.contracts.Bug;
import models.enums.Priority;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ChangePriorityOfABug implements Command {

    public static final String TITLE_OF_THE_BUG = "title of the bug";
    public static final String PRIORITY_HIGH_MEDIUM_OR_LOW = "priority (high, medium or low)";
    private static final String PRIORITY_OF_BUG_CHANGED = "The priority of bug '%s' changed %s -> %s.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final List<String> expectedArguments;

    private final BoardRepository boardRepository;

    public ChangePriorityOfABug(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_OF_THE_BUG);
        expectedArguments.add(PRIORITY_HIGH_MEDIUM_OR_LOW);
    }

    private String ChangePriority(String title, Priority priority) {
        Bug bug = boardRepository.findBugByTitle(title);
        String oldPriority = bug.getPriority().toString();
        bug.editPriority(priority);
        return String.format(PRIORITY_OF_BUG_CHANGED, title, oldPriority, priority.toString());
    }


    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        Priority priority = Parser.tryParseEnum(parameters.get(1), Priority.class);
        return ChangePriority(title, priority);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
