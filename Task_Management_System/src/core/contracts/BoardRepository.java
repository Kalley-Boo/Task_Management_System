package core.contracts;

import Models.BoardImpl;
import Models.CommentImpl;
import Models.Contracts.*;
import Models.Enums.*;
import Models.PersonImpl;
import Models.TeamImpl;

import java.util.List;

public interface BoardRepository {

    List<Task> getTasks();
    List<Bug> getBugs();
    List<Feedback> getFeedbacks();
    List<Story> getStories();
    List<Person> getPeople();
    List<BoardImpl> getBoards();
    List<Team> getTeams();
    void createAssignedBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, Person assignee);
    void createUnassignedAssignedBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity);
    void createAssignedStory(String title, String description, Priority priority, TaskSize size, Person assignee);
    void createUnassignedStory(String title, String description, Priority priority, TaskSize size);
    void createFeedback(String title, String description, int rating);
    void createPerson(String name);
    Board createBoard(String name);
    void createTeam(String name);
    Story findStoryByName(String name);
    Person findPersonByName(String name);
    Team findTeamByName(String name);
    Task findTaskByTitle(String title);
    Board findBoardByName(String name);
    Feedback findFeedbackByName(String feedbackName);
    Bug findBugByTitle(String bugTitle);
}
