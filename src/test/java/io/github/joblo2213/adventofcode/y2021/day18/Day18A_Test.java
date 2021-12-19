package io.github.joblo2213.adventofcode.y2021.day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static io.github.joblo2213.adventofcode.y2021.day18.SnailfishNumber.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18A_Test {

    @Test
    public void testParse() {
        assertEquals("[1,2]", parse("[1,2]").toString());
        assertEquals("[[1,2],3]", parse("[[1,2],3]").toString());
        assertEquals("[[1,9],[8,5]]", parse("[[1,9],[8,5]]").toString());
        assertEquals("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]", parse("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]").toString());
    }

    @Test
    public void testReduce() {
        assertEquals("[[[[0,9],2],3],4]", parse("[[[[[9,8],1],2],3],4]").toString());
        assertEquals("[7,[6,[5,[7,0]]]]", parse("[7,[6,[5,[4,[3,2]]]]]").toString());
        assertEquals("[[6,[5,[7,0]]],3]", parse("[[6,[5,[4,[3,2]]]],1]").toString());
    }

    @Test
    public void testAdd() {
        SnailfishNumber s1 = parse("[1,1]");
        s1.add(parse("[2,2]"));
        s1.add(parse("[3,3]"));
        s1.add(parse("[4,4]"));
        assertEquals("[[[[1,1],[2,2]],[3,3]],[4,4]]", s1.toString());
    }

    @Test
    public void test0() {
        SnailfishNumber s1 = parse("[[[[4,3],4],4],[7,[[8,4],9]]]");
        s1.add(parse("[1,1]"));
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", s1.toString());
    }

    @Test
    public void test1() throws Exception {
        SnailfishNumber s1 = parse("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]");
        s1.add(parse("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"));
        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", s1.toString());
        s1.add(parse("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]"));
        assertEquals("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]", s1.toString());
        s1.add(parse("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]"));
        assertEquals("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]", s1.toString());
        s1.add(parse("[7,[5,[[3,8],[1,4]]]]"));
        assertEquals("[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]", s1.toString());
        s1.add(parse("[[2,[2,2]],[8,[8,1]]]"));
        assertEquals("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]", s1.toString());
        s1.add(parse("[2,9]"));
        assertEquals("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]", s1.toString());
        s1.add(parse("[1,[[[9,3],9],[[9,0],[0,7]]]]"));
        assertEquals("[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]", s1.toString());
        s1.add(parse("[[[5,[7,4]],7],1]"));
        assertEquals("[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]", s1.toString());
        s1.add(parse("[[[[4,2],2],6],[8,7]]"));
        assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", s1.toString());
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Disabled on GH Actions")
    public void task() throws Exception {
        new Day18A().run();
    }
}
