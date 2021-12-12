package io.github.joblo2213.adventofcode.y2021.day12;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12B_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(36, new Day12B().run(ofMulti("""
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end
                """)));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(103, new Day12B().run(ofMulti("""
                dc-end
                HN-start
                start-kj
                dc-start
                dc-HN
                LN-dc
                HN-end
                kj-sa
                kj-HN
                kj-dc
                """)));
    }

    @Test
    public void test3() throws Exception {
        assertEquals(3509, new Day12B().run(ofMulti("""
                fs-end
                he-DX
                fs-he
                start-DX
                pj-DX
                end-zg
                zg-sl
                zg-pj
                pj-he
                RW-he
                fs-DX
                pj-RW
                zg-RW
                start-pj
                he-WI
                zg-he
                pj-fs
                start-RW
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day12B().run();
    }
}
