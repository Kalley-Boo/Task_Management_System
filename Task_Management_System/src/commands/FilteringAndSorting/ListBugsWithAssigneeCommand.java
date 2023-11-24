package commands.FilteringAndSorting;

import models.contracts.Bug;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;


public class ListBugsWithAssigneeCommand implements Command {
    private final BoardRepository boardRepository;
    public static final String NO_TASKS_FOUND = "No bugs with assignee were found";

    public ListBugsWithAssigneeCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String listBugsWithAssignee() {
        List<Bug> bugsWithAssignee = new ArrayList<>();
        for (Bug bug : boardRepository.getBugs()) {
            if (bug.getAssignee() != null) {
                bugsWithAssignee.add(bug);
            }
        }
        if (bugsWithAssignee.isEmpty()) {
            return NO_TASKS_FOUND;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Bug bug : bugsWithAssignee) {
                stringBuilder.append("Name of the bug: ");
                stringBuilder.append(bug.getTitle()).append("\n");
                stringBuilder.append("Name of the assignee: ");
                stringBuilder.append(bug.getAssignee()).append("\n");
            }
            return new String(stringBuilder).trim();
        }

    }

    @Override
    public String execute(List<String> parameters) {
        return listBugsWithAssignee();
    }

    @Override
    public List<String> getExpectedArguments() {
        return new ArrayList<>();
    }
}
