package io.github.joblo2213.adventofcode.y2021.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Helper class that provides easy ways of parsing the input of a puzzle.
 * <p>
 * Use {@link #toString()} to just get the content of the file.
 */
@SuppressWarnings("unused")
public class PuzzleInput implements Iterable<String> {

    /**
     * By default, the puzzle input should be located in a file called <code>puzzle_input.txt</code>
     * inside the same package as the class that uses this input.
     * <p>
     * This is mandatory if you use {@link #read(Class)}
     */
    public static final String DEFAULT_NAME = "puzzle_input.txt";

    private final List<String> inputs;
    private final String delimiter;

    private PuzzleInput(Collection<String> inputs, String delimiter) {
        this.inputs = new ArrayList<>(inputs);
        this.delimiter = delimiter;
        //remove possibly empty line at end of file
        final int last = this.inputs.size() - 1;
        if (this.inputs.get(last).isBlank()) this.inputs.remove(last);
    }


    /**
     * Read the puzzle input from a file / resource defined by the given url using a buffered reader.
     * UTF-8 Charset must be used.
     *
     * @param resource the resource file that should be the puzzle input
     * @return a new instance of the puzzle input
     * @throws IOException if an io exception occurred while reading, see {@link URL#openStream()}
     */
    public static PuzzleInput read(URL resource) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8));
        final List<String> lines = br.lines().collect(Collectors.toList());
        br.close();
        return new PuzzleInput(lines, "\n");
    }

    /**
     * Read the puzzle input for the given class using a buffered reader.
     * <p>
     * The puzzle input should be located in a file called <code>puzzle_input.txt</code>
     * inside the same package as the given class. UTF-8 Charset must be used.
     *
     * @param clazz clazz that will use the input
     * @return a new instance of the puzzle input
     * @throws IOException if an io exception occurred while reading, see {@link URL#openStream()}
     */
    public static PuzzleInput read(Class<?> clazz) throws IOException {
        return PuzzleInput.read(Optional.ofNullable(clazz.getResource(DEFAULT_NAME)).orElseThrow(() ->
                new IOException("puzzle_input.txt not found for " + clazz.getName()))
        );
    }

    /**
     * Read the puzzle input for the class from which this method is called.
     * Identical to calling {@link #read(Class)} with <code>getClass()</code> passed as parameter.
     * <p>
     * The puzzle input should be located in a file called <code>puzzle_input.txt</code>
     * inside the same package as the calling class. UTF-8 Charset must be used.
     *
     * @return a new instance of the puzzle input
     * @throws IOException if an io exception occurred while reading, see {@link URL#openStream()}
     */
    public static PuzzleInput read() throws IOException {
        return PuzzleInput.read(findCaller());
    }

    /**
     * Create a new puzzle input from the given arguments as lines
     * <p>
     * The string arguments passed will be treated as one line each.
     * They will <b>not</b> be split up at {@code \n}.
     *
     * @param in Any number of lines which will make up the new puzzle input
     * @return the created puzzle input
     */
    public static PuzzleInput of(String... in) {
        return new PuzzleInput(List.of(in), "\n");
    }

    /**
     * Creates a new puzzle input from the given arguments with the given delimiter.
     * <p>
     * They will <b>not</b> be split up further at occurrences of the delimiter.
     *
     * @param delimiter a character (or a sequence of characters if desired)
     *                  which should be added between the inputs if joined to a string
     * @param in        individual input elements also called {@code lines}
     * @return the created puzzle input
     */
    public static PuzzleInput withDelimiter(String delimiter, String... in) {
        return new PuzzleInput(List.of(in), delimiter);
    }

    /**
     * Splits a multiline string into lines and constructs a new puzzle input from it
     *
     * @param in multiline input string that will be the new puzzle input
     * @return the created puzzle input
     */
    public static PuzzleInput ofMulti(String in) {
        return ofMulti(in, "\n");
    }

    /**
     * Splits an input string at a given delimiter to create a new puzzle input
     *
     * @param in        input string that will be the new puzzle input
     * @param delimiter character (or a sequence of characters if desired)
     *                  at which the input string should be split
     * @return the created puzzle input
     */
    public static PuzzleInput ofMulti(String in, String delimiter) {
        return PuzzleInput.of(in).withDelimiter(delimiter);
    }

    /**
     * Returns a {@link Collector} that converts the input elements to strings and accumulates
     * them into a new {@code PuzzleInput}.
     * <p>
     * Identical to calling {@link #collect(String)} with {@code \n} delimiter
     *
     * @param <T> the type of the input elements
     * @return a {@code Collector} which collects all the input elements into a
     * {@code PuzzleInput}, in encounter order
     */
    public static <T> Collector<T, ?, PuzzleInput> collect() {
        return new CollectorImpl<>("\n");
    }

    /**
     * Returns a {@link Collector} that converts the input elements to strings and accumulates
     * them into a new {@code PuzzleInput}.
     *
     * @param <T>       the type of the input elements
     * @param delimiter the delimiter separating the individual input elements
     * @return a {@code Collector} which collects all the input elements into a
     * {@code PuzzleInput}, in encounter order
     */
    public static <T> Collector<T, ?, PuzzleInput> collect(String delimiter) {
        return new CollectorImpl<>(delimiter);
    }

    /**
     * Utility method for getting the class that is calling this method by searching the currents stack trace.
     * <p>
     * If called from the puzzle input class it will skipp to the next stack trace element wich allows using this in
     * other methods.
     *
     * @return class calling this method
     * @throws RuntimeException if the algorithm failed to determine a calling class.
     *                          This should not happen in practice
     */
    private static Class<?> findCaller() {
        final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 2; i < stack.length; i++) {
            if (!stack[i].getClassName().equals(PuzzleInput.class.getName())) {
                try {
                    return Class.forName(stack[i].getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);//should never occur
                }
            }
        }
        throw new RuntimeException("calling class could not be found");
    }

    /**
     * Creates a new puzzle input that is split at a different delimiter.
     * <p>
     * This puzzle input remains untouched.
     *
     * @param delimiter a character (or a sequence of characters if desired)
     *                  which should be used to split up the contents of the
     *                  puzzle input into lines
     * @return a new puzzle input with lines split at the given delimiter
     */
    public PuzzleInput withDelimiter(String delimiter) {
        final List<String> inputs = new LinkedList<>();
        final String raw = toString();
        int start = 0, end;
        //find next occurrence of new delimiter
        while (start < raw.length() && (end = raw.indexOf(delimiter, start)) != -1) {
            //add content up to delimiter to buffer if not empty
            if (end > start) inputs.add(raw.substring(start, end));
            //continue after delimiter
            start = end + delimiter.length();
        }
        //no more occurrences of delimiter, add content behind last delimiter to buffer
        if (start < raw.length()) inputs.add(raw.substring(start));
        return new PuzzleInput(inputs, delimiter);
    }

    /**
     * @return the delimiter separating the individual input lines (<code>\n</code> by default)
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * Utility method for getting the delimiter as character instead of string. See {@link #getDelimiter()}
     * <p>
     * Make sure that the delimiter is only a single character when calling this.
     *
     * @return the delimiter character separating the individual inputs (<code>\n</code> by default)
     * @throws IllegalStateException thrown if the delimiter consists of multiple chars
     */
    public char getDelimiterChar() throws IllegalStateException {
        if (delimiter.length() != 1) throw new IllegalStateException("multi char delimiter: " + delimiter);
        return delimiter.charAt(0);
    }

    /**
     * @return the lines of the input file as stream.
     */
    public Stream<String> lines() {
        return getLines().stream();
    }

    /**
     * @return the puzzle input as list of strings
     */
    public List<String> getLines() {
        return inputs;
    }

    /**
     * Parses the lines of the input as integer before returning the stream.
     */
    public IntStream ints() {
        return lines().mapToInt(Integer::parseInt);
    }

    /**
     * Parses the lines of the input as integer before returning the list.
     */
    public List<Integer> getInts() {
        return ints().boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Parses the lines of the input as double before returning the stream.
     */
    public DoubleStream doubles() {
        return lines().mapToDouble(Double::parseDouble);
    }

    /**
     * Parses the lines of the input as double before returning the list.
     */
    public List<Double> getDoubles() {
        return doubles().boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Parses the lines of the input as long before returning the stream.
     */
    public LongStream longs() {
        return lines().mapToLong(Long::parseLong);
    }

    /**
     * Parses the lines of the input as long before returning the list.
     */
    public List<Long> getLongs() {
        return longs().boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Parses the lines of the input using the provided mapper before returning the stream.
     * See {@link Stream#map(Function)}
     */
    public <R> Stream<R> map(Function<String, ? extends R> mapper) {
        return getLines().stream().map(mapper);
    }

    /**
     * Parses the lines of the input using the provided mapper before returning the list.
     * See {@link Stream#map(Function)}
     */
    public <R> List<R> mapToList(Function<String, ? extends R> mapper) {
        return map(mapper).collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Split this puzzle input around places of the given regular expression, creating a list of puzzle inputs.
     * The puzzle input will not be changed.
     * The inputs returned by the method will have the same delimiter and this puzzle input.
     * <p>
     * For finer control over the regex use {@link #split(Pattern)}
     *
     * @param regex regex at which the input should be split
     * @return new Puzzle input split at regex
     */
    public List<PuzzleInput> split(String regex) {
        return split(input -> input.split(regex));
    }

    /**
     * Split this puzzle input around places of the given regular expression, creating a list of puzzle inputs.
     * The puzzle input will not be changed.
     * The inputs returned by the method will have the same delimiter and this puzzle input.
     *
     * @param pattern compiled regex pattern at which the input should be split
     * @return new Puzzle input split at regex
     */
    public List<PuzzleInput> split(Pattern pattern) {
        return split(pattern::split);
    }

    /**
     * Utility method for {@link #split(Pattern)} & {@link #split(String)} that uses the {@code splitter} function
     * to split this puzzle input into a list of strings and then converts these strings into new puzzle inputs
     * with the same delimiter as this puzzle input
     *
     * @param splitter the function that determines where the puzzle input should be split
     * @return list of new puzzle inputs
     */
    private List<PuzzleInput> split(Function<String, String[]> splitter) {
        final String[] split = splitter.apply(toString());
        return Arrays.stream(split).map(part ->
                PuzzleInput.of(part).withDelimiter(delimiter)
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return an iterator that can be used for iterating over the individual puzzle input elements
     */
    @Override
    public Iterator<String> iterator() {
        return inputs.iterator();
    }

    /**
     * @return recreates a string from the input by joining the elements with the delimiter in between
     */
    @Override
    public String toString() {
        return switch (inputs.size()) {
            case 0 -> "";
            case 1 -> inputs.get(0);
            default -> lines().collect(Collectors.joining(delimiter));
        };
    }

    /**
     * Utility class implementing a {@link Collector} for accumulating objects into a puzzle input.
     * Is only used by {@link #collect()} and {@link #collect(String)}.
     *
     * @param <T> the type of elements to be collected
     */
    @SuppressWarnings("ClassCanBeRecord")
    private static final class CollectorImpl<T> implements Collector<T, List<String>, PuzzleInput> {

        private final String delimiter;

        private CollectorImpl(String delimiter) {
            this.delimiter = delimiter;
        }

        @Override
        public Supplier<List<String>> supplier() {
            return LinkedList::new;
        }

        @Override
        public BiConsumer<List<String>, T> accumulator() {
            return (list, t) -> list.add(String.valueOf(t));
        }

        @Override
        public BinaryOperator<List<String>> combiner() {
            return (left, right) -> {
                left.addAll(right);
                return left;
            };
        }

        @Override
        public Function<List<String>, PuzzleInput> finisher() {
            return list -> new PuzzleInput(list, delimiter);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
        }
    }
}
