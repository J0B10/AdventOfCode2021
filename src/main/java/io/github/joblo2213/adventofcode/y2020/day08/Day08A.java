package io.github.joblo2213.adventofcode.y2020.day08;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;

public class Day08A extends Task {

    @Override
    public long run(PuzzleInput input) {
        long accumulator = 0;
        List<Command> instructions = input.mapToList(Command::parse);
        boolean[] visited = new boolean[instructions.size()];
        int pointer = 0;

        //run instructions till loop is detected, then abort without executing
        while (!visited[pointer]) {
            visited[pointer] = true;
            Command command = instructions.get(pointer);

            //run instruction
            switch (command.instruction) {
                case acc -> {
                    accumulator += command.argument;
                    pointer++;
                }
                case jmp -> pointer += command.argument;
                case nop -> pointer++;
            }
        }

        return accumulator;
    }

    enum Instruction {
        acc, jmp, nop
    }

    public record Command(Instruction instruction, int argument) {

        public static Command parse(String s) throws IllegalArgumentException {
            String[] args = s.split(" ");
            if (args.length != 2) throw new IllegalArgumentException("invalid command: " + s);
            Instruction instruction = Instruction.valueOf(args[0]);
            int argument = Integer.parseInt(args[1].replace("+", ""));
            return new Command(instruction, argument);
        }
    }
}

