import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SportsActivityLoggerTest {
    private SportsActivityLogger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        logger = new SportsActivityLogger();
        System.setOut(new PrintStream(outContent));  // Redirect system output to the output stream for testing
    }

    @Test
    public void testLogActivity() {
        logger.logActivity("Running", 30);
        assertEquals(1, logger.getNumberOfActivities(), "There should be one activity logged.");
        assertEquals("Running - 30 minutes", logger.getLastActivity(), "The activity description should match.");
    }

    @Test
    public void testViewActivities() {
        logger.logActivity("Swimming", 45);
        logger.viewActivities();

        String actualOutput = normalizeLineEndings(outContent.toString());
        String expectedOutput = normalizeLineEndings("Logged Activities:\nSwimming - 45 minutes");
        assertEquals(expectedOutput, actualOutput, "The view output should match the logged activities.");
    }

    @Test
    public void testCalculateTotalTime() {
        logger.logActivity("Cycling", 60);
        logger.logActivity("Walking", 30);
        logger.calculateTotalTime();
        String expectedMessage = "Total time spent on sports this week: 90 minutes";
        assertTrue(normalizeLineEndings(outContent.toString()).contains(expectedMessage), "The total time should be calculated as 90 minutes.");
    }

    private String normalizeLineEndings(String input) {
        return input.replace("\r\n", "\n").trim();
    }
}