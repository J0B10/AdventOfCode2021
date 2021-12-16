package io.github.joblo2213.adventofcode.y2021.day16;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16B_Test {

    @Test
    public void test() throws Exception {
        assertEquals(3, new Day16B().run(ofMulti("C200B40A82")));
        assertEquals(54, new Day16B().run(ofMulti("04005AC33890")));
        assertEquals(7, new Day16B().run(ofMulti("880086C3E88112")));
        assertEquals(9, new Day16B().run(ofMulti("CE00C43D881120")));
        assertEquals(1, new Day16B().run(ofMulti("D8005AC2A8F0")));
        assertEquals(0, new Day16B().run(ofMulti("F600BC2D8F")));
        assertEquals(0, new Day16B().run(ofMulti("9C005AC2F8F0")));
        assertEquals(1, new Day16B().run(ofMulti("9C0141080250320F1802104A08")));
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Disabled on GH Actions")
    public void task() throws Exception {
        new Day16B().run();
    }
}
