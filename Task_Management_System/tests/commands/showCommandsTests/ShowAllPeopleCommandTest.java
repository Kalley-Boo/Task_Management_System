package commands.showCommandsTests;

import commands.contracts.Command;
import commands.createCommands.CreatePersonCommand;
import commands.showCommands.ShowAllPeopleCommand;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleCommandTest {
    private static final String ALL_PEOPLE_HEADER = "---All people with their tasks---";
    private static final String NO_PEOPLE = "There are no people.";

    BoardRepository boardRepository;
    @BeforeEach
    public void setUp(){
        this.boardRepository = new BoardRepositoryImpl();
    }
    @Test
    public void executeShouldReturnThePersonInTheRepository(){
        Command create = new CreatePersonCommand(boardRepository);
        List<String> args = new ArrayList<>();
        args.add("test1");
        create.execute(args);
        Command show = new ShowAllPeopleCommand(boardRepository);
        StringBuilder expected = new StringBuilder();
        expected.append(ALL_PEOPLE_HEADER).append("\n");
        expected.append(boardRepository.findPersonByName(args.get(0)).print());
        expected.append("\n");
        expected.append(ALL_PEOPLE_HEADER);
        Assertions.assertEquals(new String(expected), show.execute(new ArrayList<>()));

    }

    @Test
    public void executeShouldReturnMessageWhenThereAreNoPeople(){
        Command show = new ShowAllPeopleCommand(boardRepository);
        Assertions.assertEquals(NO_PEOPLE, show.execute(new ArrayList<>()));
    }

    @Test
    public void getArguments_should_return_a_list(){
        Command command = new ShowAllPeopleCommand(boardRepository);
        Assertions.assertEquals(command.getExpectedArguments().size(), 0);
    }
}
