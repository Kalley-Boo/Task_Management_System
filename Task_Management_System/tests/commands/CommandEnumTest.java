package commands;

import commands.enums.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandEnumTest {

    @Test
    public void testGetCommandForAllEnums() {
        for (CommandType commandType : CommandType.values()) {
            String testName = "testGetCommand_" + commandType.name();
            assertEquals(commandType.getCommand(), commandType.getCommand(), testName);
        }
    }
}
