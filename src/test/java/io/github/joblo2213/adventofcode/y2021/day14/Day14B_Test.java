package io.github.joblo2213.adventofcode.y2021.day14;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14B_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(1588, new Day14B(10).run(ofMulti("""
                NNCB
                                
                CH -> B
                HH -> N
                CB -> H
                NH -> C
                HB -> C
                HC -> B
                HN -> C
                NN -> C
                BH -> H
                NC -> B
                NB -> B
                BN -> B
                BB -> N
                BC -> B
                CC -> N
                CN -> C
                """)));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(2188189693529L, new Day14B().run(ofMulti("""
                NNCB
                                
                CH -> B
                HH -> N
                CB -> H
                NH -> C
                HB -> C
                HC -> B
                HN -> C
                NN -> C
                BH -> H
                NC -> B
                NB -> B
                BN -> B
                BB -> N
                BC -> B
                CC -> N
                CN -> C
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day14B().run();
    }
}
