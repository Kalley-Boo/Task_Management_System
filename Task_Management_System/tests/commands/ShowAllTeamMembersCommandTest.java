package commands;

import Models.Contracts.Person;
import Models.Contracts.Team;
import Models.PersonImpl;
import Models.TeamImpl;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ShowAllTeamMembersCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ShowTeamMembersCommand showTeamMembersCommand;

    @Test
    public void testExecute_ShouldReturnCorrectResult_WhenCalledWithValidParameters() {
        String teamName = "team1";
        Person member1 = new PersonImpl("Persontest");
        boardRepository.createTeam(teamName);
        Team team1 = boardRepository.findTeamByName(teamName);



        String result = showTeamMembersCommand.execute(Arrays.asList(teamName));
    }
}
