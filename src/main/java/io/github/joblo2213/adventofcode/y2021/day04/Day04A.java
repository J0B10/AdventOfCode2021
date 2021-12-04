package io.github.joblo2213.adventofcode.y2021.day04;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class Day04A extends Task{
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> inputs = input.split(Pattern.compile("\\n\\s*\\n", Pattern.MULTILINE));
        int[] nums = inputs.remove(0).withDelimiter(",").ints().toArray();
        List<BingoBoard> boards = inputs.stream().map(BingoBoard::parse).toList();
        Optional<BingoBoard> bingo = Optional.empty();
        for (int i = 0; i < nums.length && bingo.isEmpty(); i++) {
            final int mark = nums[i];
            boards.forEach(b -> b.mark(mark));
            bingo = boards.stream().filter(BingoBoard::hasBingo).findAny();
        }
        long unmarked_sum = bingo.orElseThrow().getUnMarked().stream().mapToInt(Map.Entry::getKey).sum();
        int lastNum = bingo.get().getLast().orElseThrow().getKey();
        return unmarked_sum * lastNum;
    }
}
