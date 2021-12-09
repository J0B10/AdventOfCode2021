package io.github.joblo2213.adventofcode.y2021.day09;

record Pos(int x, int y) {
    int getValue(int[][] heightmap) {
        return heightmap[x][y];
    }
}
