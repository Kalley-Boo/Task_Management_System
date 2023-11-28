package filteringAndSorting;

import commands.FilteringAndSorting.FilterFeedbackByStatus;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterFeedbackByStatusTest {
    private BoardRepository boardRepository;
    private FilterFeedbackByStatus filterFeedbackByStatus;

    @BeforeEach
    public void setUp() {
        this.boardRepository = new BoardRepositoryImpl();
        this.filterFeedbackByStatus = new FilterFeedbackByStatus(boardRepository);
    }

    @Test
    public void filterFeedbackByStatusTest(){
        boardRepository.createFeedback("Feedback1000", "jahdfakfhak", 2);
        boardRepository.createFeedback("Feedback2000", "jahdfakfhak", 2);
        boardRepository.createFeedback("Feedback3000", "jahdfakfhak", 2);

        ArrayList param = new ArrayList<>();
        param.add("New");
        String result = filterFeedbackByStatus.execute(param);
        assertEquals("Feedback1000\n" +
                        "NEW\n" +
                        "Feedback2000\n" +
                        "NEW\n" +
                        "Feedback3000\n" +
                        "NEW\n",
                result);

    }
}
