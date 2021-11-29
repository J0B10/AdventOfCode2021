package io.github.joblo2213.adventofcode.y2020.day19;


import java.util.Arrays;
import java.util.HashMap;

/**
 * Alphabet defined by rule 0 and it's sub-rules.
 */
public class Alphabet {

    private final HashMap<Integer, Rule> rules = new HashMap<>();

    /**
     * Parse a rule from the given string and add it to the array of (sub-)rules.
     *
     * @param string string representation of a rule as given by the puzzle input
     */
    public void addRule(String string) {
        String[] split = string.split(":");
        if (split.length != 2) throw new InvalidRuleInstructionException(string);
        int key = Integer.parseInt(split[0]);
        Rule rule = parseRuleInstruction(split[1].trim());
        setRule(key, rule);
    }

    /**
     * Helper method that parses a rule from an instruction.
     *
     * @param string instruction of the rule
     * @return rule as object
     */
    private Rule parseRuleInstruction(String string) {
        if (string.contains("|")) {
            //parse multi list rules
            String[] split = string.split("\\|");
            if (split.length != 2) throw new InvalidRuleInstructionException(string);
            try {
                if (parseRuleInstruction(split[0].trim()) instanceof ListRule either) {
                    if (parseRuleInstruction(split[1].trim()) instanceof ListRule or) {
                        return new MultiListRule(either, or);
                    }
                }
            } catch (InvalidRuleInstructionException ignored) {
                //will be thrown anyway but with full string
            }
            throw new InvalidRuleInstructionException(string);

        } else if (string.startsWith("\"") && string.endsWith("\"") && string.length() == 3) {
            //parse single char rules
            return new SingleCharRule(string.charAt(1));

        } else {
            //parse list rules
            String[] split = string.split(" ");
            try {
                int[] rules = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                return new ListRule(this, rules);
            } catch (NumberFormatException e) {
                throw new InvalidRuleInstructionException(string);
            }
        }
    }

    /**
     * Check if the given string matches the rules from this alphabet.
     *
     * @param string any string that should be checked
     * @return result, true if the string matches, false otherwise
     */
    public boolean matches(String string) {
        return getRule(0).tryMatch(string).stream().anyMatch(Match::isFull);
    }

    /**
     * Retrieve the rule with the given key.
     *
     * @param key key assigned to the rule
     * @return the rule definition as object
     */
    public Rule getRule(int key) {
        // if (key >= rules.length || key < 0 || rules[key] == null)
        if (!rules.containsKey(key))
            throw new IllegalArgumentException("rule " + key + " is not defined");
        return rules.get(key);
    }

    void setRule(int key, Rule rule) {
        rules.put(key, rule);
    }

    public static class InvalidRuleInstructionException extends IllegalArgumentException {
        public InvalidRuleInstructionException(String instruction) {
            super("invalid rule '" + instruction + "'");
        }
    }
}
