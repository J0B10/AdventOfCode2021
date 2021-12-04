package io.github.joblo2213.adventofcode.y2021.day04;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BingoBoard {

    record Pos(int x, int y) {
    }

    private final Map<Integer, Pos> layout = new HashMap<>();

    private final Set<Pos> marked = new HashSet<>();

    private final int size;

    private Pos lastMarked;

    private BingoBoard(int size) {
        this.size = size;
    }

    public static BingoBoard parse(PuzzleInput board) {
        int x = 0, y = 0;
        BingoBoard b = new BingoBoard(board.lineCount());
        for (String line : board) {
            for (int i : PuzzleInput.of(line.trim().split("\\s+")).getInts()) {
                b.layout.put(i, new Pos(x, y));
                x++;
            }
            x = 0;
            y++;
        }
        return b;
    }

    public void mark(int i) {
        lastMarked = layout.get(i);
        Optional.ofNullable(lastMarked).ifPresent(marked::add);
    }

    public boolean hasBingo() {
        if (lastMarked == null) return false;
        int horizontal = countMarked(1, 0) + countMarked(-1, 0) - 1;
        if (horizontal == size) return true;
        int vertical = countMarked(0, 1) + countMarked(0, -1) - 1;
        /* Diagonal Bingo's don't exist

        if (vertical == size) return true;
        int diagonalL = countMarked(-1, -1) + countMarked(1, 1) - 1;
        if (diagonalL == size) return true;
        int diagonalR = countMarked(1, -1) + countMarked(-1, 1) - 1;
        return diagonalR == size;
        */
        return vertical == size;
    }

    private int countMarked(int dx, int dy) {
        if (lastMarked == null) return 0;
        Pos next;
        int count = 1;
        while (true) {
            next = new Pos(lastMarked.x + dx * count, lastMarked.y + dy * count);
            if (marked.contains(next)) count++;
            else return count;
        }
    }

    public Set<Entry<Integer, Pos>> getUnMarked() {
        return layout.entrySet().stream().filter(e -> !marked.contains(e.getValue())).collect(Collectors.toSet());
    }

    public Optional<Entry<Integer, Pos>> getLast() {
        return Optional.ofNullable(lastMarked).flatMap(pos ->
                layout.entrySet().stream()
                        .filter(e -> e.getValue().equals(pos))
                        .findAny()
        );
    }
}
