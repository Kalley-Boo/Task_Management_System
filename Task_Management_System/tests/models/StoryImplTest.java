package models;

import models.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryImplTest {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        5, "Problem", "Problem when logging", models.enums.Priority.HIGH, TaskSize.MEDIUM, new PersonImpl("Petkov")));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        5, "Problem with the system", "Logging", models.enums.Priority.HIGH, TaskSize.MEDIUM, new PersonImpl("Petkov")));
    }

    @Test
    public void testEditAssignee() {
        StoryImpl story = new StoryImpl(
                5, "Problem with the system", "Problem when logging", models.enums.Priority.HIGH, TaskSize.MEDIUM, new PersonImpl("Petkov"));
        PersonImpl person = new PersonImpl("Dimo Dimitrov");
        story.editAssignee(person);
        assertEquals(story.getAssignee().getName(), "Dimo Dimitrov");
    }
    @Test
    public void testEditStatus(){
        StoryImpl story = new StoryImpl(
                5, "Problem with the system", "Problem when logging", models.enums.Priority.HIGH, TaskSize.MEDIUM, new PersonImpl("Petkov"));
        story.editStatus(StatusStory.IN_PROGRESS);
        assertEquals(story.getStatus(), StatusStory.IN_PROGRESS);
    }

    @Test
    public void testEditSize(){
        StoryImpl story = new StoryImpl(
                5, "Problem with the system", "Problem when logging", models.enums.Priority.HIGH, TaskSize.MEDIUM, new PersonImpl("Petkov"));
        story.editSize(TaskSize.LARGE);
        assertEquals(story.getSize(), TaskSize.LARGE);
    }

    @Test
    public void testEditPriority(){
        StoryImpl story = new StoryImpl(
                5, "Problem with the system", "Problem when logging", models.enums.Priority.HIGH, TaskSize.MEDIUM, new PersonImpl("Petkov"));
        story.editPriority(Priority.LOW);
        assertEquals(story.getPriority(), Priority.LOW);
    }
}




