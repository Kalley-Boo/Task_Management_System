package commands.showCommands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Printer;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ShowTaskActivityCommand implements Command {
    public static final String THE_TASK = "the task's title";
    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    private final List<String> expectedArguments;
    private final BoardRepository boardRepository;

    public ShowTaskActivityCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(THE_TASK);
    }
    private String showTaskActivity(String title) {
        return Printer.historyPrinter(this.boardRepository.findTaskByTitle(title).getHistory());
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        return showTaskActivity(parameters.get(0));
    }

    @Override
    public List<String> getExpectedArguments() {
        return expectedArguments;
    }
}
