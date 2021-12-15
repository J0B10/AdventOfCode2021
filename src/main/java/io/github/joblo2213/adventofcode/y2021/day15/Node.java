package io.github.joblo2213.adventofcode.y2021.day15;

import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class Node {

    final int x;
    final int y;

    final int riskLevel;
    Node last;
    int g;
    int h;

    Node(int x, int y, int riskLevel) {
        this.x = x;
        this.y = y;
        this.riskLevel = riskLevel;
    }

    Stream<Node> last() {
        if (last == null) return Stream.empty();
        else return Stream.concat(Stream.of(last), last.last());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int manhattenDistance(Node node) {
        return abs(node.x - this.x) + abs(node.y - this.y);
    }

    public int f() {
        return g + h;
    }
}
