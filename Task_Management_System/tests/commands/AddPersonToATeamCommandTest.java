package commands;

import commands.contracts.Command;
import commands.createCommands.CreatePersonCommand;
import commands.createCommands.CreateTeamCommand;
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
//        Assertions.assertEquals(boardRepository.findTeamByName("Team1").get);// TODO
    }

}
