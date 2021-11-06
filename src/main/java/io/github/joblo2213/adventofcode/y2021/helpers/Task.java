package io.github.joblo2213.adventofcode.y2021.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public abstract class Task {

    private static final File RESULTS_MD_FILE;

    static {
        RESULTS_MD_FILE = Optional.ofNullable(System.getenv("RESULTS_MD_FILE"))
                .map(File::new).map(File::getAbsoluteFile).orElse(null);
        if (RESULTS_MD_FILE != null) {
            if (!RESULTS_MD_FILE.getParentFile().exists()) {
                //noinspection ResultOfMethodCallIgnored
                RESULTS_MD_FILE.getParentFile().mkdirs();
            }
        }
    }

    /**
     * Utility method that prints a tasks result to console and optionally adds it to a markdown styled results file.
     * The file must be set as RESULTS_MD_FILE environment variable.
     *
     * @param label  label assigned to the result, usually the tasks class name
     * @param result any result value that should be logged (using its toString() method)
     */
    protected static void logResult(String label, Object result) {
        if (RESULTS_MD_FILE != null) {
            try (PrintWriter out = new PrintWriter(new FileWriter(RESULTS_MD_FILE, StandardCharsets.UTF_8, true))) {
                out.printf("* **%s**: `%s`  \n", label, result);
            } catch (IOException ignored) {
            }
        }
        System.out.printf("%4s%9s: %s%n", "⭐", label, result);
    }

    /**
     * Tries finding the puzzle input file for the current task and running it.
     * <p>
     * See {@link PuzzleInput#read(Class)}
     *
     * @return the solution of the task
     * @throws Exception any uncaught exceptions occurring while running the task
     */
    @SuppressWarnings("UnusedReturnValue")
    public long run() throws Exception {
        long result = run(PuzzleInput.read(getClass()));
        logResult(getClass().getSimpleName(), result);
        return result;
    }

    /**
     * Runs the task with the provided puzzle input.
     *
     * @param input input for which the task should be run
     * @return the solution of the task
     */
    public abstract long run(PuzzleInput input) throws Exception;
}
