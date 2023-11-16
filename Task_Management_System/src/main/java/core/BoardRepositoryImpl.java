package core;

import Models.*;
import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Contracts.Team;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import core.contracts.BoardRepository;
import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person with name %s not found.";

    private final List<Task> tasks;
    private final List<BoardImpl> boards;
    private static List<TeamImpl> teams = null;
    private final List<Person> people;

    public BoardRepositoryImpl() {
        this.tasks = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.people = new ArrayList<>();
    }


    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    @Override
    public List<BoardImpl> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public void createBug(int id, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history) {
        BugImpl bug = new BugImpl(title, description, stepsToReproduce, priority, severity, status, assignee, comments, history);
        this.tasks.add(bug);
    }

    @Override
    public void createStory(int id, String title, String description, Priority priority, TaskSize size, TaskStatus status, PersonImpl assignee, List<CommentImpl> comments, List<String> history) {
        StoryImpl story = new StoryImpl(title, description, priority, size, status, assignee, comments, history);
        this.tasks.add(story);
    }

    @Override
    public void createFeedback(int id, String title, String description, int rating, TaskStatus status, List<CommentImpl> comments, List<String> history) {
        FeedbackImpl feedback = new FeedbackImpl(title, description, rating, status, comments, history);
        this.tasks.add(feedback);
    }

    @Override
    public void createPerson(String name) {
        this.people.add(new PersonImpl(name));
    }

    @Override
    public void createBoard(String name) {
        this.boards.add(new BoardImpl(name));
    }

    @Override
    public void createTeam(String name) {
        this.teams.add(new TeamImpl(name));
    }

    public static void createANewBoardInATeam(String boardName, String teamName) {
        int a = 0;
        for (TeamImpl team : teams
        ) {
            if (teams.contains(team)) {
                a = teams.indexOf(team);
                break;
            }
            BoardImpl board = new BoardImpl(boardName);
            teams.get(a).addBoard(board);

        }

    }

    public Person findPersonByName(String name) {
        for (Person p : this.people) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new PersonNotFoundException(String.format(PERSON_NOT_FOUND_EXCEPTION, name));
    }
}
