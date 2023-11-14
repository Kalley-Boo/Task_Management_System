package models;

import Models.CommentImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import Models.FeedbackImpl;
import Models.PersonImpl;
import Models.StoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class Story {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        1, "Problem", "Problem when logging", Models.Enums.Priority.HIGH, TaskSize.MEDIUM, Models.Enums.TaskStatus.ACTIVE, new PersonImpl("Ivan"), new ArrayList<CommentImpl>(), new ArrayList<>()));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        1, "Problem with the system", "Logging", Models.Enums.Priority.HIGH, TaskSize.MEDIUM, Models.Enums.TaskStatus.ACTIVE, new PersonImpl("Ivan"), new ArrayList<CommentImpl>(), new ArrayList<>()));
    }

}

