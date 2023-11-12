package core;

import Models.Board;
import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Contracts.Team;
import core.contracts.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    private final List<Task> tasks;
    private final List<Board> boards;
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
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public void createTask() {

    }

    @Override
    public void createPerson(String name) {

    }

    @Override
    public void createBoard(String name) {

    }

    @Override
    public void createTeam(String name) {

    }
}