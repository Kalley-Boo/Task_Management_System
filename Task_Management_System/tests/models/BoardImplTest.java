package models;

import Models.BoardImpl;
import Models.Contracts.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class BoardImplTest {

    public String VALID_NAME = "Test1";
    public String INVALID_NAME = "Test";

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        Board board = new BoardImpl(VALID_NAME);
        Assertions.assertEquals(VALID_NAME, board.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_ArgumentIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BoardImpl(INVALID_NAME);
        });
    }

}