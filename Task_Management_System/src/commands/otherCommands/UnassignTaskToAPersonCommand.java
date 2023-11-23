package commands.otherCommands;

import Models.Contracts.Bug;
import Models.Contracts.Person;
import Models.Contracts.Story;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;


public class UnassignTaskToAPersonCommand implements Command {
    public static final String THE_TITLE = "the title of the task";
    private final List<String> expectedArguments;
    public static final String COMMAND_IS_DONE = "Task with name %s has been unassigned to person with name %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final BoardRepository boardRepository;

    public UnassignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_TITLE);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String taskName = parameters.get(0);
        return unassignTaskToAPerson(taskName);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private String unassignTaskToAPerson(String taskName) {
        Bug bug = boardRepository.findBugByTitle(taskName);
        Person oldAssignee;
        if (bug != null){
            oldAssignee = bug.getAssignee();
            bug.editAssignee(null);
            oldAssignee.removeTask(boardRepository.findTaskByTitle(taskName));
        }else {
            Story story = boardRepository.findStoryByName(taskName);
            oldAssignee = story.getAssignee();
            story.editAssignee(null);
            oldAssignee.removeTask(boardRepository.findTaskByTitle(taskName));
        }

        return String.format(COMMAND_IS_DONE, taskName, oldAssignee.getName());
    }
}
