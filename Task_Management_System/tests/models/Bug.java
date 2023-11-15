package models;
import Models.BugImpl;
import Models.CommentImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskStatus;
import Models.PersonImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class Bug {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        "Problem", "Problem when logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, TaskStatus.ACTIVE, new PersonImpl("Ivan"), new ArrayList<CommentImpl>(), new ArrayList<>()));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        "Problem to resolve", "Logging", new ArrayList<String>(), Priority.LOW, Severity.MINOR, TaskStatus.ACTIVE, new PersonImpl("Ivan"), new ArrayList<CommentImpl>(), new ArrayList<>()));
    }
}
