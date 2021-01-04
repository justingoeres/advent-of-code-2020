package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day25.Day25Service;
import org.jgoeres.adventofcode2020.common.ToClipboard;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day25Test {
    private final String PUZZLE_INPUT = "data/day25/input.txt";
    private final Day25Service day25Service = new Day25Service(PUZZLE_INPUT);

    @Test
    @Order(2)
    public void Day25AExample1() {
        Day25Service day25Service = new Day25Service("data/day25/example1.txt");
        final long EXPECTED = 14897079;
        long result = 0;
        try {
            result = day25Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(EXPECTED, result);
    }

    @Test
    @Order(1)
    public void Day25A() {
        final int EXPECTED = 10548634;
        long result = 0;
        try {
            result = day25Service.doPartA();
            ToClipboard.set(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 14239149
        // 14239149 too high
        assertEquals(EXPECTED, result);
    }
}
