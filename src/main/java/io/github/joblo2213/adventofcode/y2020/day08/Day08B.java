package io.github.joblo2213.adventofcode.y2020.day08;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;
import java.util.OptionalLong;

import static io.github.joblo2213.adventofcode.y2020.day08.Day08A.Command;
import static io.github.joblo2213.adventofcode.y2020.day08.Day08A.Instruction;

public class Day08B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<Command> instructions = input.mapToList(Command::parse);
        for (int i = 0; i < instructions.size(); i++) {
            Command command = instructions.get(i);
            if (command.instruction() == Instruction.acc) continue;
            //change jmp to nop and nop to jump
            Command newCmd = switch (command.instruction()) {
                case jmp -> new Command(Instruction.nop, command.argument());
                case nop -> new Command(Instruction.jmp, command.argument());
                default -> throw new RuntimeException("unreachable");
            };
            instructions.set(i, newCmd);
            //test run
            OptionalLong result = tryRun(instructions);
            if (result.isPresent()) {
                return result.getAsLong();
            }
            //if unsuccessful revert and try again with next instruction
            instructions.set(i, command);
        }
        throw new Exception("unsolvable");
    }

    public OptionalLong tryRun(List<Command> instructions) {
        long accumulator = 0;
        boolean[] visited = new boolean[instructions.size()];
        int pointer = 0;

        while (pointer < instructions.size()) {
            if (visited[pointer]) return OptionalLong.empty(); //Loop, abort unsuccessful!
            visited[pointer] = true;
            Command command = instructions.get(pointer);

            //run instruction
            switch (command.instruction()) {
                case acc -> {
                    accumulator += command.argument();
                    pointer++;
                }
                case jmp -> pointer += command.argument();
                case nop -> pointer++;
            }
        }
        return OptionalLong.of(accumulator); //End of instructions, abort successful!
    }
}

