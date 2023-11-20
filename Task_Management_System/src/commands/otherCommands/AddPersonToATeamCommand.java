package commands.otherCommands;

import Models.Contracts.Person;
import Models.Contracts.Team;
import commands.contracts.Command;
import core.contracts.BoardRepository;
import util.Validator;

import java.util.List;

public class AddPersonToATeamCommand implements Command {

    private static final String PERSON_ADDED_TO_A_TEAM = "%s added to team %s";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final BoardRepository boardRepository;

    public AddPersonToATeamCommand(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    private String AddPersonToATeam(String personName, String teamName){
        Person person = boardRepository.findPersonByName(personName);
        Team team = boardRepository.findTeamByName(teamName);
        team.addMember(person);
        return String.format(PERSON_ADDED_TO_A_TEAM, personName, teamName);
    }

    @Override
    public String execute(List<String> parameters) {
        Validator.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        String teamName = parameters.get(1);
        return AddPersonToATeam(personName, teamName);
    }
}
