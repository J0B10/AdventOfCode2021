package io.github.joblo2213.adventofcode.y2021.day15;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;

public class Day15B extends Day15A {
    @Override
    public long run(PuzzleInput input) throws Exception {
        parse(input);
        expandCaveMap();
        boolean result = aStar();
        assert result;
        return dest.last().mapToLong(n -> n.riskLevel).sum() - nodes[0].riskLevel + dest.riskLevel;
    }

    void expandCaveMap() {
        Node[] expanded = new Node[nodes.length * 5 * 5];
        int width = this.width * 5;
        int height = expanded.length / width;
        int old_height = this.nodes.length / this.width;
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                int val = nodes[(x % this.width) + (y % old_height) * this.width].riskLevel + x / this.width + y / old_height;
                while (val > 9) val -= 9;
                expanded[x + y * width] = new Node(x, y, val);
            }
        }
        nodes = expanded;
        this.width = width;
    }
}
