package commands.showCommands;

import models.contracts.Bug;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class FilterBugsByStatus implements Command {
    public static final String TITLE = "Enter status (will show all bugs that are with this status)";
    public static final String NO_BUGS = "There are no bugs with this status currently.";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public FilterBugsByStatus(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(TITLE);
    }

    private String filterBugsByStatus(String status){
        List<Bug> filteredBugs = boardRepository.getBugs()
                .stream()
                .filter(bug -> bug.getTaskStatus().toString().equalsIgnoreCase(status))
                .toList();
        if(filteredBugs.isEmpty()){
            return NO_BUGS;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(Bug bug : filteredBugs){
            stringBuilder.append(bug.getTitle()).append("\n");
            stringBuilder.append(bug.getTaskStatus()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return filterBugsByStatus(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
