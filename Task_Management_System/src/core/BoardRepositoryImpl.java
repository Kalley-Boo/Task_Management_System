package core;

import Models.*;
import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import core.contracts.BoardRepository;
import exceptions.BoardNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.TaskNotFoundException;
import exceptions.TeamNotFoundException;
import util.Printer;

import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    private int nextId = 1;

    private static final String TEAM_NOT_FOUND_EXCEPTION = "Team with name %s not found.";
    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person with name %s not found.";
    private static final String BOARD_NOT_FOUND_EXCEPTION = "Board with name %s not found.";
    private static final String TASK_NOT_FOUND_EXCEPTION = "Task with title %s not found.";

    private final List<Task> tasks;
    private final List<Bug> bugs;
    private final List<Feedback> feedbacks;
    private final List<Story> stories;
    private final List<BoardImpl> boards;
    private final List<TeamImpl> teams;
    private final List<Person> people;

    public BoardRepositoryImpl() {
        this.tasks = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.people = new ArrayList<>();
    }

    // ----------------GETTERS------------------
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
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


    //-----------------CREATE------------------
    @Override
    public void createAssignedBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status, PersonImpl assignee) {
        BugImpl bug = new BugImpl(nextId++, title, description, stepsToReproduce, priority, severity, assignee);
        this.tasks.add(bug);
        this.bugs.add(bug);
    }

    @Override
    public void createUnassignedAssignedBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, TaskStatus status) {
        BugImpl bug = new BugImpl(nextId++, title, description, stepsToReproduce, priority, severity);
        this.tasks.add(bug);
        this.bugs.add(bug);
    }

    @Override
    public void createAssignedStory(String title, String description, Priority priority, TaskSize size, PersonImpl assignee) {
        StoryImpl story = new StoryImpl(nextId++, title, description, priority, size, assignee);
        this.tasks.add(story);
        this.stories.add(story);
    }

    @Override
    public void createUnassignedStory(String title, String description, Priority priority, TaskSize size) {
        StoryImpl story = new StoryImpl(nextId++, title, description, priority, size);
        this.tasks.add(story);
        this.stories.add(story);
    }

    @Override
    public void createFeedback(String title, String description, int rating) {
        FeedbackImpl feedback = new FeedbackImpl(nextId, title, description, rating);
        this.tasks.add(feedback);
        this.feedbacks.add(feedback);
    }


    @Override
    public void createPerson(String name) {
        this.people.add(new PersonImpl(name));
    }

    @Override
    public Board createBoard(String name) {
        BoardImpl board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public void createTeam(String name) {
        this.teams.add(new TeamImpl(name));
    }

    //------------------FIND------------------------
    @Override
    public Person findPersonByName(String name) {
        for (Person p : this.people) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new PersonNotFoundException(String.format(PERSON_NOT_FOUND_EXCEPTION, name));
    }

    @Override
    public Team findTeamByName(String name) {
        for (Team t : this.teams) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new TeamNotFoundException(String.format(TEAM_NOT_FOUND_EXCEPTION, name));
    }

    @Override
    public Task findTaskByTitle(String title) {
        for (Task task : this.tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        throw new TaskNotFoundException(String.format(TASK_NOT_FOUND_EXCEPTION, title));
    }

    @Override
    public Board findBoardByName(String name) {
        for (Board board : this.boards) {
            if (board.getName().equals(name)) {
                return board;
            }
        }
        throw new BoardNotFoundException(String.format(BOARD_NOT_FOUND_EXCEPTION, name));
    }


    //------------------OTHERS----------------------
    @Override
    public void assignTaskToAPerson(String personName, String title) {
        findPersonByName(personName).addTask(findTaskByTitle(title));
    }
    @Override
    public void unassignTaskToAPerson(String personName, String taskName) {
        findPersonByName(personName).removeTask(findTaskByTitle(taskName));
            }

    public Feedback findFeedbackByName(String feedbackName){
        int a = 0;
        for (Feedback feedback : feedbacks
        ) {if(feedback.getTitle().equals(feedbackName))
        { a =feedbacks.indexOf(feedback);break;}
        } return feedbacks.indexOf(a);
    }
    @Override
    public void changeRatingOfAFeedback(String feedbackName, int rating) {
        findFeedbackByName(feedbackName).setRating(rating);
    }
    @Override
    public void changeStatusOfAFeedback(String feedbackName, StatusFeedback status){

    }

    @Override
    public void createANewBoardInATeam(String boardName, String teamName) {
        findTeamByName(teamName).addBoard(createBoard(boardName));
    }

}