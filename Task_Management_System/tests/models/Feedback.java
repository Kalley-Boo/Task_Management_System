package models;

import Models.CommentImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskStatus;
import Models.FeedbackImpl;
import Models.PersonImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class Feedback {

    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        1, "Problem", "Problem when logging", 5, TaskStatus.ACTIVE, new ArrayList<CommentImpl>(), new ArrayList<>()));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        1, "Problem for solving", "Logging", 5, TaskStatus.ACTIVE, new ArrayList<CommentImpl>(), new ArrayList<>()));
    }


}
