package util;

import core.EngineImpl;
import core.contracts.Engine;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.start();
    }
}