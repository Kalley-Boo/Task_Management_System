package core;

import commands.CommandType;
import commands.CreatePersonCommand;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import core.contracts.CommandFactory;
import util.Parser;

public class CommandFactoryImpl implements CommandFactory {
    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";

    @Override
    public Command createCommand(String commandTypeValue, BoardRepository boardRepository) {
        CommandType commandType = Parser.tryParseCommandType(commandTypeValue);

        switch (commandType) {
            case CREATETASK:
                //
            case CREATETEAM:
                //
            case CREATEPERSON:
                return new CreatePersonCommand(boardRepository);
            case CTREATEBOARD:
                //
            default:
                throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandTypeValue));
        }
    }
}
