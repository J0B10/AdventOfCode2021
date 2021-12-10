package io.github.joblo2213.adventofcode.y2021.day10;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10B_Test {

    @Test
    public void testComplete() {
        assertEquals("}}]])})]", Day10B.complete("[({(<(())[]>[[{[]{<()<>>").orElseThrow());
        assertEquals(")}>]})", Day10B.complete("[(()[<>])]({[<{<<[]>>(").orElseThrow());
        assertEquals("}}>}>))))", Day10B.complete("(((({<>}<{<{<>}{[]{[]{}").orElseThrow());
        assertEquals("]]}}]}]}>", Day10B.complete("{<[[]]>}<{[{[{[]{()[[[]").orElseThrow());
        assertEquals("])}>", Day10B.complete("<{([{{}}[<[[[<>{}]]]>[]]").orElseThrow());
    }

    @Test
    public void testScore() {
        assertEquals(294, Day10B.score("])}>"));
        assertEquals(288957, Day10B.score("}}]])})]"));
        assertEquals(5566, Day10B.score(")}>]})"));
        assertEquals(1480781, Day10B.score("}}>}>))))"));
        assertEquals(995444, Day10B.score("]]}}]}]}>"));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(288957, new Day10B().run(ofMulti("""
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                {([(<{}[<>[]}>{[]{[(<()>
                (((({<>}<{<{<>}{[]{[]{}
                [[<[([]))<([[{}[[()]]]
                [{[{({}]{}}([{[{{{}}([]
                {<[[]]>}<{[{[{[]{()[[[]
                [<(<(<(<{}))><([]([]()
                <{([([[(<>()){}]>(<<{{
                <{([{{}}[<[[[<>{}]]]>[]]
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day10B().run();
    }
}
