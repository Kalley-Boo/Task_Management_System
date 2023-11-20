package core.contracts;

import commands.contracts.Command;

public interface CommandFactory {

    Command createCommand(int commandNumber, BoardRepository boardRepository);
}
