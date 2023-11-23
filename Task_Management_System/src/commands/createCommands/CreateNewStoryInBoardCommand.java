package commands.createCommands;

import Models.Contracts.Board;
import Models.Contracts.Person;
import Models.Enums.Priority;
import Models.Enums.TaskSize;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateNewStoryInBoardCommand implements Command {
    public static final String A_TITLE = "a title for the story (10-100 characters)";
    public static final String DESCRIPTION = "description (10-500 characters)";
    public static final String PRIORITY = "priority (HIGH, MEDIUM, LOW)";
    public static final String SIZE = "size (LARGE, MEDIUM, SMALL)";
    public static final String ASSIGNEE = "assignee(type unassigned to leave it unassigned)";
    public static final String BOARD = "board on which this story should be";

    public static final int EXPECTED_PARAMETERS_COUNT = 6;
    private static final String ASSIGNED_STORY_CREATED = "Story with title %s was created and assigned to %s!";
    private static final String UNASSIGNED_STORY_CREATED = "Story with title %s was created!";
    public static final String INVALID_TITLE_LENGTH = "The length of the title must be 10-100";
    public static final String STORY_EXISTS = "Story with this title already exists.";
    public static final String INVALID_DESCRIPTION_LENGTH = "The length of the description must be 10-500";
    private final List<String> expectedArguments;

    private final BoardRepository boardRepository;

    public CreateNewStoryInBoardCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(A_TITLE);
        expectedArguments.add(DESCRIPTION);
        expectedArguments.add(PRIORITY);
        expectedArguments.add(SIZE);
        expectedArguments.add(ASSIGNEE);
        expectedArguments.add(BOARD);
    }

    private boolean storyExists(String title) {
        return boardRepository.getStories().stream().anyMatch(story -> story.getTitle().equals(title));
    }

    private String creteAssignedStory(String title, String description, Priority priority, TaskSize taskSize, Person assignee, Board board) {
        boardRepository.createAssignedStory(title, description, priority, taskSize, assignee);
        board.addTask(boardRepository.findTaskByTitle(title));
        return String.format(ASSIGNED_STORY_CREATED, title, assignee.getName());
    }

    private String createUnassignedStory(String title, String description, Priority priority, TaskSize taskSize, Board board) {
        boardRepository.createUnassignedStory(title, description, priority, taskSize);
        board.addTask(boardRepository.findTaskByTitle(title));
        return String.format(UNASSIGNED_STORY_CREATED, title);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String title = parameters.get(0);
        Validator.validateStringLength(title, 10, 100, INVALID_TITLE_LENGTH);

        if (storyExists(title)) {
            throw new InvalidInputException(STORY_EXISTS);
        }

        String description = parameters.get(1);
        Validator.validateStringLength(description, 10, 500, INVALID_DESCRIPTION_LENGTH);
        Priority priority = Parser.tryParseEnum(parameters.get(2), Priority.class);
        TaskSize taskSize = Parser.tryParseEnum(parameters.get(3), TaskSize.class);
        Board board = boardRepository.findBoardByTitle(parameters.get(5));

        if (parameters.get(4).equalsIgnoreCase("unassigned")) {
            return this.createUnassignedStory(title, description, priority, taskSize, board);
        } else {
            Person assignee = boardRepository.findPersonByName(parameters.get(4));
            return creteAssignedStory(title, description, priority, taskSize, assignee, board);
        }
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
