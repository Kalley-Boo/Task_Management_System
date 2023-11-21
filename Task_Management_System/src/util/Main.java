package util;

import Models.BugImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.PersonImpl;
import core.EngineImpl;
import core.contracts.Engine;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.start();

    }
}