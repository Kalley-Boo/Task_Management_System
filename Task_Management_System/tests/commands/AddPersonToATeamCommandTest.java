package commands;

import commands.changeCommands.ChangePriorityOfABug;
import commands.contracts.Command;
import commands.createCommands.CreatePersonCommand;
import commands.createCommands.CreateTeamCommand;
import commands.otherCommands.AddPersonToATeamCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddPersonToATeamCommandTest {

    List<String> args;
    BoardRepository boardRepository;



    @BeforeEach
    public void setUp(){
        boardRepository = new BoardRepositoryImpl();
        args = new ArrayList<>();
        args.add("person");
        args.add("team1");
    }

    @Test
    public void addPersonCommand_shouldWork_when_validInput(){
        CreateTeamCommand command = new CreateTeamCommand(boardRepository);
        List<String> teamArgs = new ArrayList<>();
        teamArgs.add("Team1");
        command.execute(teamArgs);
        List<String> personArgs = new ArrayList<>();
        personArgs.add("Person");
        Command createPerson = new CreatePersonCommand(boardRepository);
        createPerson.execute(personArgs);
        Command addPerson = new AddPersonToATeamCommand(boardRepository);
        List<String> addPersonArgs = new ArrayList<>();
        addPersonArgs.add("Person");
        addPersonArgs.add("Team1");
        addPerson.execute(addPersonArgs);
        Assertions.assertEquals(1, boardRepository.findTeamByName("Team1").getMembers().size());
    }

    @Test
    public void getArguments_should_return_a_list(){
        Assertions.assertEquals(new AddPersonToATeamCommand(boardRepository).getExpectedArguments().size(), 2);
    }

}
