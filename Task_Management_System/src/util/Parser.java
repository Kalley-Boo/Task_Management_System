package util;

import commands.CommandType;

public abstract class Parser {

    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";
    public static final String NO_SUCH_ENUM = "There is no %s in %ss.";


    public static CommandType tryParseCommandType(String value) {
        try {
            return CommandType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // This exception means that the user has entered not existing command.
            throw new IllegalArgumentException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, value));
        }
    }
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {
        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }
}
