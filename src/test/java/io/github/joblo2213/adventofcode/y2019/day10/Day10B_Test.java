package io.github.joblo2213.adventofcode.y2019.day10;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10B_Test {

    private Asteroid center;


    @Test
    public void test1() {
        long result = new Day10B(new Asteroid(11, 13)).run(PuzzleInput.ofMulti("""
                .#..##.###...#######
                ##.############..##.
                .#.######.########.#
                .###.#######.####.#.
                #####.##.#.##.###.##
                ..#####..#.#########
                ####################
                #.####....###.#.#.##
                ##.#################
                #####.##.###..####..
                ..######..##.#######
                ####.##.####...##..#
                .#####..#.######.###
                ##...#.##########...
                #.##########.#######
                .####.#.###.###.#.##
                ....##.##.###..#####
                .#.#.###########.###
                #.#.#.#####.####.###
                ###.##.####.##.#..##
                """));
        assertEquals(802, result);
    }


    @BeforeEach
    public void prepare() throws Exception {
        center = new Day10A().findCenter(PuzzleInput.read()).center();
    }

    @Test
    public void task() throws Exception {
        new Day10B(center).run();
    }
}
