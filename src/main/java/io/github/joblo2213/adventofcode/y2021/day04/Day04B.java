package io.github.joblo2213.adventofcode.y2021.day04;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.joblo2213.adventofcode.y2021.helpers.TODO.*;

public class Day04B extends Task{
    @Override
    public long run(PuzzleInput input) throws Exception{
        List<PuzzleInput> inputs = input.split(Pattern.compile("\\n\\s*\\n", Pattern.MULTILINE));
        int[] nums = inputs.remove(0).withDelimiter(",").ints().toArray();
        Set<BingoBoard> boards = inputs.stream().map(BingoBoard::parse).collect(Collectors.toSet());
        BingoBoard last = null;
        for (int i = 0; i < nums.length && !boards.isEmpty(); i++) {
            final int mark = nums[i];
            boards.forEach(b -> b.mark(mark));
            last = boards.stream().findAny().orElseThrow();
            boards.removeIf(BingoBoard::hasBingo);
        }
        assert boards.isEmpty();
        assert last != null;
        long unmarked_sum = last.getUnMarked().stream().mapToInt(Map.Entry::getKey).sum();
        int lastNum = last.getLast().orElseThrow().getKey();
        return unmarked_sum * lastNum;
    }
}
