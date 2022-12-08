import day6.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.DayInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    private static DayInterface day;
    private final String basePath = "src/main/resources/" + day.getClass().getSimpleName().toLowerCase();

    @BeforeAll
    public static void setUp() {
        day = new Day6();
    }

    @Test
    void puzzle1_sample1() {
        String result = day.puzzle1(basePath + "/sample_p1_1.txt");
        assertEquals(7, Integer.parseInt(result));
    }

    @Test
    void puzzle1_sample2() {
        String result = day.puzzle1(basePath + "/sample_p1_2.txt");
        assertEquals(5, Integer.parseInt(result));
    }

    @Test
    void puzzle1_sample3() {
        String result = day.puzzle1(basePath + "/sample_p1_3.txt");
        assertEquals(6, Integer.parseInt(result));
    }

    @Test
    void puzzle1_sample4() {
        String result = day.puzzle1(basePath + "/sample_p1_4.txt");
        assertEquals(10, Integer.parseInt(result));
    }

    @Test
    void puzzle1_sample5() {
        String result = day.puzzle1(basePath + "/sample_p1_5.txt");
        assertEquals(11, Integer.parseInt(result));
    }

    @Test
    void puzzle1_actual() {
        String result = day.puzzle1(basePath + "/input.txt");
        System.out.println(day.getClass().getSimpleName() + " puzzle 1 result: " + result);
    }

    @Test
    void puzzle2_sample1() {
        String result = day.puzzle2(basePath + "/sample_p2_1.txt");
        assertEquals(19, Integer.parseInt(result));
    }

    @Test
    void puzzle2_sample2() {
        String result = day.puzzle2(basePath + "/sample_p2_2.txt");
        assertEquals(23, Integer.parseInt(result));
    }

    @Test
    void puzzle2_sample3() {
        String result = day.puzzle2(basePath + "/sample_p2_3.txt");
        assertEquals(23, Integer.parseInt(result));
    }

    @Test
    void puzzle2_sample4() {
        String result = day.puzzle2(basePath + "/sample_p2_4.txt");
        assertEquals(29, Integer.parseInt(result));
    }

    @Test
    void puzzle2_sample5() {
        String result = day.puzzle2(basePath + "/sample_p2_5.txt");
        assertEquals(26, Integer.parseInt(result));
    }

    @Test
    void puzzle2_actual() {
        String result = day.puzzle2(basePath + "/input.txt");
        System.out.println(day.getClass().getSimpleName() + " puzzle 2 result: " + result);
    }
}
