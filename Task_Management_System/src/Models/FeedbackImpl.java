package Models;

import Models.Contracts.Comment;
import Models.Contracts.Feedback;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {
    @Override
    public List<Comment> getComments() {
        return null;
    }
}
