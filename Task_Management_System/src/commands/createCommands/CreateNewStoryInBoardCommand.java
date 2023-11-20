package commands.createCommands;

import Models.Contracts.Board;
import Models.Contracts.Person;
import Models.Enums.Priority;
import Models.Enums.TaskSize;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateNewStoryInBoardCommand implements Command {
    private final List<String> expectedArguments;

    public static final int EXPECTED_PARAMETERS_COUNT = 6;
    private static final String ASSIGNED_STORY_CREATED = "Story with title %s was created and assigned to %s!";
    private static final String UNASSIGNED_STORY_CREATED = "Story with title %s was created!";
    private final BoardRepository boardRepository;


    public CreateNewStoryInBoardCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add("a title for the story");
        expectedArguments.add("description");
        expectedArguments.add("priority");
        expectedArguments.add("size");
        expectedArguments.add("assignee(type unassigned to leave it unassigned)");
        expectedArguments.add("board on which this story should be");
    }

    private String creteAssignedStory(String title, String description, Priority priority, TaskSize taskSize, Person assignee, Board board){
        boardRepository.createAssignedStory(title, description, priority, taskSize, assignee);
        board.addTask(boardRepository.findTaskByTitle(title));
        return String.format(ASSIGNED_STORY_CREATED, title, assignee.getName());
    }

    private String createUnassignedStory(String title, String description, Priority priority, TaskSize taskSize, Board board){
        boardRepository.createUnassignedStory(title, description, priority, taskSize);
        board.addTask(boardRepository.findTaskByTitle(title));
        return String.format(UNASSIGNED_STORY_CREATED, title);
    }


    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = Parser.tryParseEnum(parameters.get(2), Priority.class);
        TaskSize taskSize = Parser.tryParseEnum(parameters.get(3), TaskSize.class);
        Board board = boardRepository.findBoardByName(parameters.get(4));
        if (parameters.get(4).equalsIgnoreCase("unassigned")){
            return this.createUnassignedStory(title, description, priority, taskSize, board);

        }else {
            Person assignee = boardRepository.findPersonByName(parameters.get(4));
            return creteAssignedStory(title, description, priority, taskSize, assignee, board);
        }
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
