package core;

import Models.*;
import Models.Contracts.*;
import Models.Enums.Priority;
import Models.Enums.Severity;
import Models.Enums.TaskSize;
import Models.Enums.TaskStatus;
import core.contracts.BoardRepository;
import exceptions.PersonNotFoundException;
import exceptions.TeamNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person with name %s not found.";
    private static final String TEAM_NOT_FOUND_EXCEPTION = "Team with name %s not found.";


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

    //------------------OTHERS----------------------
    @Override
    public void assignTaskToAPerson(String personName, String taskName){
        int a = 0;
        for (Task task : tasks
        ) {if(task.getTitle().equals(taskName))
        { a = tasks.indexOf(task);
            break;}}
        for (Person p : this.people) {
            if (p.getName().equals(personName)) {
                p.addTask(tasks.get(a));
            }
    }}

    @Override
    public void createTeam(String name) {
        this.teams.add(new TeamImpl(name));
    }

    @Override
    public void createANewBoardInATeam(String boardName, String teamName) {
        int a = 0;
        for (TeamImpl team : teams
        ) {if(team.getName().equals(teamName))
        { a = teams.indexOf(team);
            break;}}
            BoardImpl board = new BoardImpl(boardName);
            teams.get(a).addBoard(board);
        }


    @Override
    public void showAllTeamBoards(String teamName) {

        int a = 0;
        for (TeamImpl team : teams
        ) {if(team.getName().equals(teamName))
            { a = teams.indexOf(team);
                break;}}
            for (BoardImpl board:teams.get(a).getBoards()) {
                board.print();
            }

        }
    @Override
    public void showBoardSActivity (String boardname){
        int a = 0;
        for (BoardImpl board : boards
        ) {if(board.getName().equals(boardname))
        { a = boards.indexOf(board);
            break;}}

        for (HistoryLogImpl historyLog: boards.get(a).getHistoryLogs()
             ) {
            historyLog.viewInfo();

        }
        }


        public Person findPersonByName (String name){
            for (Person p : this.people) {
                if (p.getName().equals(name)) {
                    return p;
                }
            }
            throw new PersonNotFoundException(String.format(PERSON_NOT_FOUND_EXCEPTION, name));
        }

        public Team findTeamByName (String name){
            for(Team t : this.teams){
                if(t.getName().equals(name)){
                    return t;
                }
            }
            throw new TeamNotFoundException(String.format(TEAM_NOT_FOUND_EXCEPTION, name));
        }
    }
