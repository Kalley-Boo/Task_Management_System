package commands.changeCommands;

import Models.Contracts.Bug;
import Models.Enums.StatusBug;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Parser;
import util.Validator;

import java.util.List;

public class ChangeStatusOfABug implements Command {

    private static final String STATUS_OF_BUG_CHANGED = "The status of bug '%s' changed %s -> %s.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final BoardRepository boardRepository;

    public ChangeStatusOfABug(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }


    private String ChangeStatus(String title, StatusBug statusBug){
        Bug bug = boardRepository.findBugByTitle(title);
        String oldStatus = bug.getTaskStatus().toString();
        bug.editStatus(statusBug);
        return String.format(STATUS_OF_BUG_CHANGED, title, oldStatus, statusBug.toString());
    }



    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        StatusBug status = Parser.tryParseEnum(parameters.get(1), StatusBug.class);
        return ChangeStatus(title, status);
    }
}
