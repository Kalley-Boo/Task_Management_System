package commands.createCommands;

import Models.Contracts.Board;
import Models.Contracts.Person;
import Models.Enums.Priority;
import Models.Enums.Severity;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateNewBugInBoardCommand implements Command {
    private final List<String> expectedArguments;

    public static final int EXPECTED_PARAMETERS_COUNT = 7;
    private static final String ASSIGNED_BUG_CREATED = "Bug with title %s was created and assigned to %s!";
    private static final String UNASSIGNED_BUG_CREATED = "Bug with title %s was created!";

    private final BoardRepository boardRepository;

    public CreateNewBugInBoardCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a title for the bug");
        expectedArguments.add("description");
        expectedArguments.add("steps to reproduce separated by ','");
        expectedArguments.add("priority");
        expectedArguments.add("severity");
        expectedArguments.add("assignee(type unassigned to leave it unassigned)");
        expectedArguments.add("board on which this bug should be");
    }

    private String createUnassignedBug(String title, String description, List<String> steps, Priority priority, Severity severity, Board board) {
        boardRepository.createUnassignedAssignedBug(title, description, steps, priority, severity);
        board.addTask(boardRepository.findTaskByTitle(title));
        return String.format(UNASSIGNED_BUG_CREATED, title);
    }

    private String createAssignedBug(String title, String description, List<String> steps, Priority priority, Severity severity, Person person, Board board) {
        boardRepository.createAssignedBug(title, description, steps, priority, severity, person);
        board.addTask(boardRepository.findTaskByTitle(title));
        return String.format(ASSIGNED_BUG_CREATED, title, person.getName());
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String title = parameters.get(0);
        String description = parameters.get(1);
        List<String> steps = Arrays.asList(parameters.get(2).split(","));
        Priority priority = Parser.tryParseEnum(parameters.get(3), Priority.class);
        Severity severity = Parser.tryParseEnum(parameters.get(4), Severity.class);
        Board board = boardRepository.findBoardByName(parameters.get(6));

        if (parameters.get(5).equalsIgnoreCase("unassigned")){
            return this.createUnassignedBug(title, description, steps, priority, severity, board);
        } else {
            Person assignee = boardRepository.findPersonByName(parameters.get(5));
            return createAssignedBug(title, description, steps, priority, severity, assignee, board);
        }
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
