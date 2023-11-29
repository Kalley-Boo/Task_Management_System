package commands.showCommandsTests;

import models.contracts.Person;
import models.contracts.Team;
import models.PersonImpl;
import commands.showCommands.ShowTeamMembersCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

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

        String result = showTeamMembersCommand.execute(List.of(teamName));
        Assertions.assertEquals("---Members---\nPersontest PersonAaaa ", result);

    }
}
