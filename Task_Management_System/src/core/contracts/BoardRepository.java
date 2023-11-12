package core.contracts;

import Models.BoardImpl;
import Models.Contracts.Person;
import Models.Contracts.Task;
import Models.Contracts.Team;

import java.util.List;

public interface BoardRepository {

    List<Task> getTasks();
    List<Person> getPeople();
    List<BoardImpl> getBoards();
    List<Team> getTeams();
    void createBug();//finish when the tasks are implemented
    void createStory();
    void createFeedback();
    void createPerson(String name);
    void createBoard(String name);
    void createTeam(String name);

}
