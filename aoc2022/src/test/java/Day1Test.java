import day1.Day1;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.DayInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    private static DayInterface day;
    private String basePath = "src/main/resources/" + day.getClass().getSimpleName().toLowerCase();

    @BeforeAll
    public static void setUp() {
        day = new Day1();
    }

    @Test
    void puzzle1_sample() {
        String result = day.puzzle1(basePath + "/sample.txt");
        assertEquals(24000, Integer.parseInt(result));
    }

    @Test
    void puzzle1_actual() {
        String result = day.puzzle1(basePath + "/input.txt");
        System.out.println(day.getClass().getSimpleName() + " puzzle 1 result: " + result);
    }

    @Test
    void puzzle2_sample() {
        String result = day.puzzle2(basePath + "/sample.txt");
        assertEquals(45000, Integer.parseInt(result));
    }

    @Test
    void puzzle2_actual() {
        String result = day.puzzle2(basePath + "/input.txt");
        System.out.println(day.getClass().getSimpleName() + " puzzle 2 result: " + result);
    }
}
