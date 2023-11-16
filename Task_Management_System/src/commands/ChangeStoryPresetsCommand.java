package commands;

import commands.contracts.Command;
import core.contracts.BoardRepository;
import exceptions.InvalidInputException;

import java.util.List;

public class ChangeStoryPresetsCommand implements Command {
    public static final int EXPECTED_PARAMETERS_COUNT = 4;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "Change Story Presets command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);
    private final BoardRepository boardRepository;

    public ChangeStoryPresetsCommand(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }

        String storyTitle = parameters.get(0);


    }

    private String changeStoryPriority()
}
