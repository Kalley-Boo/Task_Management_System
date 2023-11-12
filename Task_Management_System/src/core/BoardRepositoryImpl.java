package core;

import Models.*;
import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Contracts.Team;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    private final List<Task> tasks;
    private final List<BoardImpl> boards;
    private final List<Team> teams;
    private final List<Person> people;

    public BoardRepositoryImpl(){
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
    public void createBug() {
        this.tasks.add(new BugImpl());
    }

    @Override
    public void createStory() {
        this.tasks.add(new StoryImpl());
    }

    @Override
    public void createFeedback() {
        this.tasks.add(new FeedbackImpl());
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
}