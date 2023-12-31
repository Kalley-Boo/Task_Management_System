package models;

import models.enums.StatusFeedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FeedbackImplTest {
    @Test
    public void constructor_Should_ThrowException_When_TitleIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        3, "Problem", "Problem when logging", 5));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsOutOfRange() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        3, "Problem when logging", "Logging", 5));
    }

    @Test
    public void testUpdateRating() {
        FeedbackImpl feedback = new FeedbackImpl(
                3, "Problem with the system", "Problem when logging", 5);
        feedback.updateRating(4);
        Assertions.assertEquals(feedback.getRating(), 4);
    }

    @Test
    public void testUpdateStatus() {
        FeedbackImpl feedback = new FeedbackImpl(
                3, "Problem with the system", "Problem when logging", 5);
        feedback.updateStatus(StatusFeedback.DONE);
        Assertions.assertEquals(feedback.getStatus(), StatusFeedback.DONE);
    }

}