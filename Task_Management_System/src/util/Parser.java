package util;

import commands.CommandType;

public abstract class Parser {

    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";

    public static CommandType tryParseCommandType(String value) {
        try {
            return CommandType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // This exception means that the user has entered not existing command.
            throw new IllegalArgumentException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, value));
        }
    }
}
