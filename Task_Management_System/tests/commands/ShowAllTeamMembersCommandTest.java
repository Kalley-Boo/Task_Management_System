package commands;

import Models.Contracts.Person;
import Models.Contracts.Team;
import Models.PersonImpl;
import Models.TeamImpl;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShowAllTeamMembersCommandTest {
    BoardRepository boardRepository = new BoardRepositoryImpl();
    private ShowTeamMembersCommand showTeamMembersCommand;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.showTeamMembersCommand = new ShowTeamMembersCommand(boardRepository);
    }

    @Test
    public void testExecute_ShouldReturnCorrectResult_WhenCalledWithValidParameters() {
        String teamName = "team1";
        Person member1 = new PersonImpl("Persontest");
        Person member2 = new PersonImpl("PersonAaaa");
        boardRepository.createTeam(teamName);
        Team team1 = boardRepository.findTeamByName(teamName);
        team1.addMember(member1);
        team1.addMember(member2);

        String result = showTeamMembersCommand.execute(Arrays.asList(teamName));
        assertEquals("---Members---\nPersontest PersonAaaa ", result);

    }
}
