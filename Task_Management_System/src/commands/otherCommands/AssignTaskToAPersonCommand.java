package commands.otherCommands;

import Models.Contracts.Bug;
import Models.Contracts.Person;
import Models.Contracts.Story;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskToAPersonCommand implements Command {
    public static final String THE_NEW_ASSIGNEE = "the new assignee's name";
    public static final String THE_TASK_TITLE = "the task's title";
    private final List<String> expectedArguments;
    public static final String COMMAND_IS_DONE = "Task with name %s has been assigned to person with name %s.";

    public static final int EXPECTED_PARAMETERS_COUNT = 2;
    private final BoardRepository boardRepository;

    public AssignTaskToAPersonCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_NEW_ASSIGNEE);
        expectedArguments.add(THE_TASK_TITLE);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String personName = parameters.get(0);
        String task = parameters.get(1);

        return assignTaskToAPerson(personName, task);
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }

    private String assignTaskToAPerson(String personName, String task) {
        Person person = boardRepository.findPersonByName(personName);
        Bug bug = boardRepository.findBugByTitle(task);
        if (bug != null){
            bug.editAssignee(person);
        }else {
            Story story = boardRepository.findStoryByName(task);
            story.editAssignee(person);
        }
        person.addTask(boardRepository.findTaskByTitle(task));
        return String.format(COMMAND_IS_DONE, task, personName);
    }
}
