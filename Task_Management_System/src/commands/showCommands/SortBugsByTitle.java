package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import models.contracts.Bug;

import java.util.Comparator;
import java.util.List;

public class SortBugsByTitle implements Command {
    public static final String NO_TASKS_FOUND = "There are no bugs currently";
    public static final String BUG_S_TITLE = "Bug's title: ";
    private final BoardRepository boardRepository;

    public SortBugsByTitle(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String sortBugsByTitle(){
        List<Bug> sortedBugs = boardRepository.getBugs()
                .stream()
                .sorted(Comparator.comparing(Bug::getTitle))
                .toList();
        if(sortedBugs.isEmpty()){
            return NO_TASKS_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Bug bug : sortedBugs){
            stringBuilder.append(BUG_S_TITLE);
            stringBuilder.append(bug.getTitle()).append("\n");
        }
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return sortBugsByTitle();
    }

    @Override
    public List<String> getExpectedArguments() {
        return null;
    }
}
