package core.contracts;

import Models.BoardImpl;
import Models.CommentImpl;
import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import Models.PersonImpl;

import java.util.List;

public interface BoardRepository {

    List<Task> getTasks();
    List<Bug> getBugs();
    List<Feedback> getFeedbacks();
    List<Story> getStories();
    List<Person> getPeople();
    List<BoardImpl> getBoards();
    List<Team> getTeams();


    void createBug(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history);

    void createStory(int id, String title, String description, Priority priority, TaskSize size, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history);

    void createFeedback(int id, String title, String description, int rating, TaskStatus status, List<CommentImpl> comments, List<String> history);

    void createPerson(String name);
    void createBoard(String name);
    void createTeam(String name);
    Person findPersonByName(String name);

    Team findTeamByName(String name);
    void createANewBoardInATeam(String boardName, String teamName);
    void showAllTeamBoards(String boardname);
    void showBoardSActivity (String boardname);

    void assignTaskToAPerson(String personName, String taskName);
    void unassignTaskToAPerson(String personName, String taskName);
    void changeRatingOfAFeedback (String feedbackName, int rating);

}
