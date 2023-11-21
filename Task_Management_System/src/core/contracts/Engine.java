package core.contracts;

import java.util.List;

public interface Engine {

    void start();

    int selectCommand();
    void processCommand(int commandNumber);
    int extractCommandNumber(String inputLine);
    List<String> collectArguments(List<String> expectedArguments);
    void showCommandsOptions();
    }
