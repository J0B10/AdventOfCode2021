package io.github.joblo2213.adventofcode.y2020.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06A_Test {

    @Test
    public void test1() {
        assertEquals(3, new Day06A().countAnsweredByAnyone(PuzzleInput.of("abc")));
    }

    @Test
    public void test2() {
        assertEquals(3, new Day06A().countAnsweredByAnyone(PuzzleInput.of("a", "b", "c")));
    }

    @Test
    public void test3() {
        assertEquals(3, new Day06A().countAnsweredByAnyone(PuzzleInput.of("ab", "ac")));
    }

    @Test
    public void test4() {
        assertEquals(1, new Day06A().countAnsweredByAnyone(PuzzleInput.of("a", "a", "a", "a")));
    }

    @Test
    void test5() {
        assertEquals(1, new Day06A().countAnsweredByAnyone(PuzzleInput.of("b")));
    }

    @Test
    public void test6() throws Exception {
        assertEquals(11, new Day06A().run(PuzzleInput.ofMulti("""
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day06A().run();
    }
}
