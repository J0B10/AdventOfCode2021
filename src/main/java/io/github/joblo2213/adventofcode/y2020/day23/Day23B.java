package io.github.joblo2213.adventofcode.y2020.day23;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.github.joblo2213.adventofcode.y2021.helpers.TODO.*;

public class Day23B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        int[] start = input.toString().chars()
                .map(c -> Character.digit(c, 10)).toArray();
        int[] full = Arrays.copyOf(start, 1000000);
        for (int i = start.length; i < full.length; i++) full[i] = i + 1;
        Game g = new Game(full);
        for (int i = 0; i < 10000000; i++) g.move();
        int a = g.cup_order[1];
        int b = g.cup_order[a];
        return  (long) a * b;
    }

    public static class Game {

        /**
         * Indizes are the cups labels,
         * values are the label of the next cup (clockwise).
         * Cup 0 does not exist so this will be always empty.
         */
        private final int[] cup_order;

        /**
         * The label of the current cup
         */
        private int current;

        public Game(int[] cups) {
            cup_order = new int[cups.length + 1];
            current = cups[0];
            for (int i = 0; i < cups.length; i++) {
                int cupLbl = cups[i];
                int nextCupLbl = cups[(i + 1) % cups.length];
                cup_order[cupLbl] = nextCupLbl;
            }
        }

        private List<Integer> removeNext3Cups() {
            Integer[] next = new Integer[3];
            int cup = current;
            for (int i = 0; i < next.length; i++) {
                cup = cup_order[cup];
                next[i] = cup;
            }
            cup_order[current] = cup_order[cup];
            return Arrays.asList(next);
        }

        private int getDestinationCup(Collection<Integer> ignore) {
            int dest = current;
            do {
                if (--dest < 1) dest = cup_order.length - 1;
            } while (ignore.contains(dest));
            return dest;
        }

        private void insertCups(int after, List<Integer> insert) {
            int successor = cup_order[after];
            cup_order[after] = insert.get(0);
            cup_order[insert.get(insert.size() - 1)] = successor;
        }

        public void move() {
            List<Integer> next3Cups = removeNext3Cups();
            int destCup = getDestinationCup(next3Cups);
            insertCups(destCup, next3Cups);
            current = cup_order[current];
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append('(').append(current).append(')');
            int cup = current;
            for (int i = 0; i < 9 && cup_order[cup] != current; i++) {
                s.append(' ').append(cup = cup_order[cup]);
            }
            if (cup_order.length > 11) s.append(" ...");
            return s.toString();
        }
    }
}
