package io.github.joblo2213.adventofcode.y2021.day08;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08B_Test {

    @Test
    public void test0() {
        Map<Character, Character> wireMap = Day08B.parseWireMap("""
                acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
                """);
        assertEquals('d', wireMap.get('a'));
        assertEquals('e', wireMap.get('b'));
        assertEquals('a', wireMap.get('c'));
        assertEquals('f', wireMap.get('d'));
        assertEquals('g', wireMap.get('e'));
        assertEquals('b', wireMap.get('f'));
        assertEquals('c', wireMap.get('g'));
    }

    @Test
    public void test1() throws Exception {
        Map<Character, Character> wireMap = Day08B.parseWireMap("""
                acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
                """);
        assertEquals(8, Day08B.parseSignal(wireMap, "acedgfb"));
        assertEquals(5, Day08B.parseSignal(wireMap, "cdfbe"));
        assertEquals(2, Day08B.parseSignal(wireMap, "gcdfa"));
        assertEquals(3, Day08B.parseSignal(wireMap, "fbcad"));
        assertEquals(7, Day08B.parseSignal(wireMap, "dab"));
        assertEquals(9, Day08B.parseSignal(wireMap, "cefabd"));
        assertEquals(6, Day08B.parseSignal(wireMap, "cdfgeb"));
        assertEquals(4, Day08B.parseSignal(wireMap, "eafb"));
        assertEquals(0, Day08B.parseSignal(wireMap, "cagedb"));
        assertEquals(1, Day08B.parseSignal(wireMap, "ab"));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(61229L, new Day08B().run(ofMulti("""
                be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
                edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
                fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
                fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
                aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
                fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
                dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
                bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
                egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
                gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day08B().run();
    }
}
