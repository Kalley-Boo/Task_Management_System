package core.contracts;

import Models.BoardImpl;
import Models.CommentImpl;
import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
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


    void createAssignedBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status, PersonImpl assignee);
    void createUnassignedAssignedBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status);
    void createAssignedStory(String title, String description, Priority priority, TaskSize size, PersonImpl assignee);
    void createUnassignedStory(String title, String description, Priority priority, TaskSize size);
    void createFeedback(String title, String description, int rating);
    void createPerson(String name);
    Board createBoard(String name);
    void createTeam(String name);


    Person findPersonByName(String name);
    Team findTeamByName(String name);
    Task findTaskByTitle(String title);
    Board findBoardByName(String name);


    void createANewBoardInATeam(String boardName, String teamName);
    void showAllTeamBoards(String boardname);
    String showBoardsActivity (String boardName);

    void assignTaskToAPerson(String personName, String taskName);
    void unassignTaskToAPerson(String personName, String taskName);
    void changeRatingOfAFeedback (String feedbackName, int rating);

}
