package io.github.joblo2213.adventofcode.y2021.day15;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.*;

public class Day15A extends Task {

    Node[] nodes;
    int width;
    Node dest;
    PriorityQueue<Node> open;
    Set<Node> closed;

    @Override
    public long run(PuzzleInput input) throws Exception {
        parse(input);
        boolean result = aStar();
        assert result;
        return dest.last().mapToLong(n -> n.riskLevel).sum() - nodes[0].riskLevel + dest.riskLevel;
    }

    int p(int x, int y) {
        return x + y * width;
    }

    void parse(PuzzleInput input) {
        width = input.getLines().get(0).length();
        nodes = new Node[width * input.lineCount()];
        int x = 0, y = 0;
        for (String line : input) {
            for (char ch : line.toCharArray()) {
                nodes[p(x, y)] = new Node(x, y, Character.getNumericValue(ch));
                x++;
            }
            y++;
            x = 0;
        }
    }

    boolean aStar() {
        dest = nodes[nodes.length - 1];
        open = new PriorityQueue<>(width / 2, Comparator.comparingInt(Node::f));
        closed = new HashSet<>();
        open.add(nodes[0]); //start node
        do {
            Node current = open.remove();
            if (current.equals(dest)) return true; //success!
            closed.add(current);
            expand(current);
        } while (!open.isEmpty());
        return false; //no path found
    }

    void expand(Node n) {
        for (Node next : adjacent(n)) {
            if (closed.contains(next)) {
                continue;
            }
            int g = n.g + next.riskLevel;
            if (!open.contains(next) || g < next.g) {
                open.remove(next); //if already existing remove and add again to update position based on new f
                next.last = n;
                next.g = g;
                next.h = next.manhattenDistance(dest);
                open.add(next);
            }
        }
        closed.add(n);
    }

    List<Node> adjacent(Node n) {
        List<Node> adjacent = new ArrayList<>(4);
        if (n.x > 0) adjacent.add(nodes[p(n.x - 1, n.y)]);
        if (n.y > 0) adjacent.add(nodes[p(n.x, n.y - 1)]);
        if (n.x < width - 1) adjacent.add(nodes[p(n.x + 1, n.y)]);
        if (n.y < (nodes.length / width) - 1) adjacent.add(nodes[p(n.x, n.y + 1)]);
        return adjacent;
    }
}
