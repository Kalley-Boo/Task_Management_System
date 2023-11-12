package core.contracts;

import Models.Board;
import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Contracts.Team;

import java.util.List;

public interface BoardRepository {

    List<Task> getTasks();
    List<Person> getPeople();
    List<Board> getBoards();
    List<Team> getTeams();
    void createTask();//finish when the tasks are implemented
    void createPerson(String name);
    void createBoard(String name);
    void createTeam(String name);

}
