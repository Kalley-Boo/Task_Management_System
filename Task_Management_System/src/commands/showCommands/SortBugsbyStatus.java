package commands.showCommands;

import Models.Contracts.Bug;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBugsbyStatus implements Command {
    public static final String NO_BUGS = "There are no bugs currently.";
    private final BoardRepository boardRepository;

    public SortBugsbyStatus(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String sortBugsByStatus(){
        List<Bug> sortedBugs = boardRepository.getBugs().stream()
                .sorted(Comparator.comparing(Bug::getTaskStatus))
                .collect(Collectors.toList());
        if(sortedBugs.isEmpty()){
            return NO_BUGS;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Bug bug : sortedBugs){
            stringBuilder.append(bug.getTitle()).append("\n");
            stringBuilder.append("The status is: ").append(bug.getTaskStatus()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return sortBugsByStatus();
    }

    @Override
    public List<String> getExpectedArguments() {
        return null;
    }
}
