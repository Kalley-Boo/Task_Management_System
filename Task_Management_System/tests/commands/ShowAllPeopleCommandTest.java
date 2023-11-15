package commands;

import commands.contracts.Command;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleCommandTest {

    @Test
    public void executeShouldReturnThePersonInTheRepository(){
        BoardRepository boardRepository = new BoardRepositoryImpl();
        Command create = new CreatePersonCommand(boardRepository);
        List<String> args = new ArrayList<>();
        args.add("aaaaa");
        create.execute(args);
        Command show = new ShowAllPeopleCommand(boardRepository);
        Assertions.assertEquals(boardRepository.getPeople().get(0).toString(), show.execute(new ArrayList<>()));

    }
}
