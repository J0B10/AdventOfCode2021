package io.github.joblo2213.adventofcode.y2020.day23;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.joblo2213.adventofcode.y2021.helpers.TODO.*;

public class Day23A extends Task{
    @Override
    public long run(PuzzleInput input) throws Exception{
        LinkedList<Integer> cups = input.toString().chars()
                .map(c -> Character.digit(c, 10)).boxed().collect(Collectors.toCollection(LinkedList::new));
        for (int i = 0; i < 100; i++) move(cups);
        int one = cups.indexOf(1);
        long result = 0;
        for (int i = 1; i < cups.size(); i++) {
            result = result * 10 + cups.get((one + i) % cups.size());
        }
        return result;
    }


    @SuppressWarnings({"ConstantConditions"})
    private static void move(LinkedList<Integer> cups) {
        int current = cups.poll();
        List<Integer> pickedUp = new LinkedList<>();
        for (int i = 0; i < 3; i++) pickedUp.add(cups.poll());
        int dest = 0;
        int i = current - 1;
        boolean loop = true;
        while (loop) {
            dest = 0;
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int cup : cups) {
                if (cup == i) {
                    loop = false;
                    break;
                } else {
                    if (cup > max) max = cup;
                    else if (cup < min) min = cup;
                    dest++;
                }
            }
            if (i < min) i = max;
            else i--;
        }
        cups.addAll(dest + 1, pickedUp);
        cups.addLast(current);
    }
}
