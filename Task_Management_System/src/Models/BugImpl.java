package Models;

import Models.Contracts.Bug;

import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    @Override
    public List<Comment> getComments() {
        return null;
    }
}
