package commands.showCommands;

import Models.Contracts.Person;
import Models.Contracts.Task;
import commands.contracts.Command;
import core.contracts.BoardRepository;

import java.util.List;

public class ShowAllTasksCommand implements Command {
    private static final String ALL_TASKS_HEADER = "---All tasks---";
    private static final String NO_TASKS = "There are no tasks.";
    private final BoardRepository boardRepository;

    public ShowAllTasksCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String showAllTasks(){
        if (boardRepository.getTasks().isEmpty()){
            return NO_TASKS;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ALL_TASKS_HEADER).append("\n");

        for (Task task : boardRepository.getTasks()) {
            stringBuilder.append(task.getId()).append(". ").append(task.getTitle()).append("\n");
            stringBuilder.append("Title: ").append(task.getTitle());
            stringBuilder.append("\n");
        }

        stringBuilder.append(ALL_TASKS_HEADER);
        return new String(stringBuilder);
    }

    @Override
    public String execute(List<String> parameters) {
        return showAllTasks();
    }

    @Override
    public List<String> getExpectedArguments() {
        return null;
    }
}
