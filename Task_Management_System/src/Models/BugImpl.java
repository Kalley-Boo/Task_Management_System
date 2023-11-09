package Models;

import Models.Contracts.Bug;
import Models.Contracts.Comment;

import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    @Override
    public List<Comment> getComments() {
        return null;
    }
}
