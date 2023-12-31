package commands.changeCommands;

import models.contracts.Bug;
import models.enums.Severity;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ChangeSeverityOfABug implements Command {
    public static final String TITLE_OF_BUG = "title of bug";
    public static final String NEW_SEVERITY = "new severity (minor, major, critical)";
    private static final String SEVERITY_OF_BUG_CHANGED = "The severity of bug '%s' changed %s -> %s.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final List<String> expectedArguments;

    private final BoardRepository boardRepository;

    public ChangeSeverityOfABug(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE_OF_BUG);
        expectedArguments.add(NEW_SEVERITY);
    }

    private String ChangeStatus(String title, Severity severity) {
        Bug bug = boardRepository.findBugByTitle(title);
        String oldSeverity = bug.getSeverity().toString();
        bug.editSeverity(severity);
        return String.format(SEVERITY_OF_BUG_CHANGED, title, oldSeverity, severity.toString());
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        Severity severity = Parser.tryParseEnum(parameters.get(1), Severity.class);
        return ChangeStatus(title, severity);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
