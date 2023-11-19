package models;

import Models.CommentImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskStatus;
import Models.FeedbackImpl;
import Models.PersonImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class FeedbackImplTest {
        @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        3,"Problem", "Problem when logging", 5));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        3,"Problem when logging", "Logging", 5));
    }
}
