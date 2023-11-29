package commands;

import commands.enums.CommandType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CommandEnumTest {

    @Test
    public void testGetCommandForAllEnums() {
        for (CommandType commandType : CommandType.values()) {
            String testName = "testGetCommand_" + commandType.name();
            Assertions.assertEquals(commandType.getCommand(), commandType.getCommand(), testName);
        }
    }
}
