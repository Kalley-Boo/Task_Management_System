package commands.createCommands;

import Models.Contracts.Board;
import Models.Contracts.Person;
import Models.Enums.Priority;
import Models.Enums.TaskSize;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.List;

public class CreateNewStoryInBoardCommand implements Command {

    public static final int EXPECTED_PARAMETERS_COUNT = 5;
    private static final String ASSIGNED_STORY_CREATED = "Story with title %s was created and assigned to %s!";
    private static final String UNASSIGNED_STORY_CREATED = "Story with title %s was created!";
    private final BoardRepository boardRepository;


    public CreateNewStoryInBoardCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
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
        if (parameters.size() != 6 && parameters.size() != 5){
            Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        }
        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = Parser.tryParseEnum(parameters.get(2), Priority.class);
        TaskSize taskSize = Parser.tryParseEnum(parameters.get(3), TaskSize.class);
        if (parameters.size() == 5){
            Board board = boardRepository.findBoardByName(parameters.get(4));
            return this.createUnassignedStory(title, description, priority, taskSize, board);
        }else {
            Person assignee = boardRepository.findPersonByName(parameters.get(4));
            Board board = boardRepository.findBoardByName(parameters.get(5));
            return creteAssignedStory(title, description, priority, taskSize, assignee, board);
        }
    }
}
