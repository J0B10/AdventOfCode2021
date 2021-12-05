package io.github.joblo2213.adventofcode.y2021.day05;

public record Pos(int x, int y) {
    @Override
    public String toString() {
        return x + "," + y;
    }
}
