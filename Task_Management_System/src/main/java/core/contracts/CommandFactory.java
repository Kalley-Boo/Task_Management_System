package core.contracts;

import commands.contracts.Command;

public interface CommandFactory {

    Command createCommand(String commandTypeValue, BoardRepository boardRepository);
}
