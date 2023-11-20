package core;

import Models.*;
import Models.Contracts.*;
import Models.Enums.*;
import core.contracts.BoardRepository;
import exceptions.*;
import util.Printer;

import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    public static final String FEEDBACK_WITH_NAME_S_WAS_NOT_FOUND = "Feedback with name %s was not found.";
    public static final String STORY_S_NOT_FOUND = "Story %s not found!";
    private int nextId = 1;

    private static final String TEAM_NOT_FOUND_EXCEPTION = "Team with name %s not found.";
    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person with name %s not found.";
    private static final String BOARD_NOT_FOUND_EXCEPTION = "Board with name %s not found.";
    private static final String TASK_NOT_FOUND_EXCEPTION = "Task with title %s not found.";
    private static final String BUG_NOT_FOUND_EXCEPTION = "Bug with title %s not found.";


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
    public void createAssignedStory(String title, String description, Priority priority, TaskSize size, Person assignee) {
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

    public Story findStoryByName(String name){
        for(Story story : this.stories){
            if(story.getName().equals(name)){
                return story;
            }
        }
        throw new TaskNotFoundException(String.format(STORY_S_NOT_FOUND, name));
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
    @Override
    public Feedback findFeedbackByName(String feedbackName) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getName().equals(feedbackName)) {
                return feedback;
            }
        } throw new IllegalArgumentException (String.format(FEEDBACK_WITH_NAME_S_WAS_NOT_FOUND, feedbackName));
    }

    @Override
    public Bug findBugByTitle(String bugTitle) {
        for (Bug bug : bugs) {
            if (bug.getTitle().equals(bugTitle)){
                return bug;
            }
        }
        throw new BugNotFoundException(String.format(BUG_NOT_FOUND_EXCEPTION, bugTitle));
    }


    //------------------OTHERS----------------------

    @Override
    public void createANewBoardInATeam(String boardName, String teamName) {
        findTeamByName(teamName).addBoard(createBoard(boardName));
    }

}