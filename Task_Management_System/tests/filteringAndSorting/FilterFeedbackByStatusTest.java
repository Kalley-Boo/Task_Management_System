package filteringAndSorting;

import commands.FilteringAndSorting.FilterFeedbackByStatus;
import core.BoardRepositoryImpl;
import core.contracts.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


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

        List<String> param = new ArrayList<>();
        param.add("New");
        String result = filterFeedbackByStatus.execute(param);
        Assertions.assertEquals("""
                        Feedback1000
                        NEW
                        Feedback2000
                        NEW
                        Feedback3000
                        NEW
                        """,
                result);

    }
}
