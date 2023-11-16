package core.contracts;

import java.util.List;

public interface Engine {

    void start();

    void processCommand(String inputLine);
    String extractCommandName(String inputLine);
    List<String> extractCommandParameters(String inputLine);
}
