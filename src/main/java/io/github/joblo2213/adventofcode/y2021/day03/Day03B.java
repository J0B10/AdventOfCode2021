package io.github.joblo2213.adventofcode.y2021.day03;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.joblo2213.adventofcode.y2021.helpers.TODO.TODO;

public class Day03B extends Task {

    @Override
    public long run(PuzzleInput input) throws Exception {
        int oxygenRating = getRating(input, this::getMostCommon);
        int co2Rating = getRating(input, this::getLeastCommon);
        return (long) oxygenRating * co2Rating;
    }

    int getRating(PuzzleInput input, BiFunction<Integer, List<String>, Character> bitCriteria) {
        List<String> ratings = new ArrayList<>(input.getLines());
        for (int i = 0; ratings.size() > 1; i++) {
            final int index = i;
            char criteria = bitCriteria.apply(i, ratings);
            ratings = ratings.stream().filter(s -> s.charAt(index) == criteria).collect(Collectors.toList());
        }
        return Integer.parseInt(ratings.get(0), 2);
    }

    char getMostCommon(int index, List<String> lines) {
        return Character.forDigit(
                (int) lines.stream().filter(s -> s.charAt(index) == '1').count() * 2 / lines.size(),
                10
        );
    }

    char getLeastCommon(int index, List<String> lines) {
        return getMostCommon(index, lines) == '0' ? '1' : '0';
    }
}
