package models;

import Models.CommentImpl;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import Models.FeedbackImpl;
import Models.PersonImpl;
import Models.StoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryImplTest {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        5, "Problem", "Problem when logging", Models.Enums.Priority.HIGH, TaskSize.MEDIUM,  new PersonImpl("Ivan")));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        5, "Problem with the system", "Logging", Models.Enums.Priority.HIGH, TaskSize.MEDIUM,  new PersonImpl("Ivan")));
    }

    @Test
    public void testEditAssignee() {
        StoryImpl story = new StoryImpl(
                5, "Problem with the system", "Problem when logging", Models.Enums.Priority.HIGH, TaskSize.MEDIUM,  new PersonImpl("Petkov"));
        PersonImpl person = new PersonImpl("Dimo Dimitrov");
        story.editAssignee(person);
        assertEquals(story.getAssignee().getName(), "Dimo Dimitrov");
    }


}
