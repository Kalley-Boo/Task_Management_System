package models;
import Models.BugImpl;
import Models.CommentImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskStatus;
import Models.PersonImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class BugImplTest {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        2, "Problem", "Problem when logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan")));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        2, "Problem", "logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, new PersonImpl("Ivan")));
    }

}
