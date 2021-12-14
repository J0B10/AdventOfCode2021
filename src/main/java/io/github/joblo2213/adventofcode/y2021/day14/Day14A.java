package io.github.joblo2213.adventofcode.y2021.day14;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.regex.Pattern.MULTILINE;

public class Day14A extends Task {

    private static final Pattern TEMPLATE_REGEX = Pattern.compile("(\\w{2}) -> (\\w)\\s*");

    //sorry if today gets a bit crappy

    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> inputs = input.split(Pattern.compile("\\n\\s*\\n", MULTILINE));
        assert inputs.size() == 2;
        String template = inputs.get(0).toString();
        Map<String, String> rules = parseRules(inputs.get(1));
        for (int i = 0; i < 10; i++) {
            template = step(template, rules);
        }
        List<Entry<Character, Long>> occurrences = template.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparingLong(Entry::getValue))
                .toList();
        long least = occurrences.get(0).getValue();
        long most = occurrences.get(occurrences.size() - 1).getValue();
        return most - least;
    }

    static Map<String, String> parseRules(PuzzleInput input) {
        record Rule(String key, String val) {
        }
        return input.map(TEMPLATE_REGEX::matcher).map(m -> {
            if (!m.matches()) throw new IllegalArgumentException(m.toString());
            return new Rule(m.group(1), m.group(2) + m.group(1).charAt(1));
        }).collect(Collectors.toMap(Rule::key, Rule::val));
    }

    static Stream<String> pairs(String s) {
        return IntStream.range(0, s.length() - 1).mapToObj(i -> s.substring(i, i + 2));
    }

    static String step(String string, Map<String, String> rules) {
        return string.charAt(0) + pairs(string)
                .parallel()
                .map(s -> rules.getOrDefault(s, s))
                .collect(Collectors.joining());
    }
}
