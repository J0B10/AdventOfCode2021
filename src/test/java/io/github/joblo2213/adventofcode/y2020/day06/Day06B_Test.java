package io.github.joblo2213.adventofcode.y2020.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

public class Day06B_Test {

    @Test
    public void test1() {
        assertEquals(3, new Day06B().countAnsweredByEveryone(PuzzleInput.of("abc")));
    }

    @Test
    public void test2() {
        assertEquals(0, new Day06B().countAnsweredByEveryone(PuzzleInput.of("a", "b", "c")));
    }

    @Test
    public void test3() {
        assertEquals(1, new Day06B().countAnsweredByEveryone(PuzzleInput.of("ab", "ac")));
    }

    @Test
    public void test4() {
        assertEquals(1, new Day06B().countAnsweredByEveryone(PuzzleInput.of("a", "a", "a", "a")));
    }

    @Test
    void test5() {
        assertEquals(1, new Day06B().countAnsweredByEveryone(PuzzleInput.of("b")));
    }

    @Test
    public void test6() throws Exception {
        assertEquals(6, new Day06B().run(PuzzleInput.ofMulti("""
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
        new Day06B().run();
    }
}
