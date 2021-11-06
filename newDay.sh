#!/bin/bash
day=${1:-$(date +"%-d")}
day2D=$(printf "%02d" "${day}")

# Make new file if it doesn't exist.
# param $1 - dir
# param $2 - fileName
# param $3 - content
function mkFile() {
    if [ -f "${2}" ]; then
      echo "File already exists: ${2}"
    else
      mkdir -p "${1}"
      echo "${3}" > "${2}"
      echo "Generated: ${2}"
    fi
}

# Make a new task file
# param $1 - A or B
function mkTaskFile() {
    dir="src/main/java/io/github/joblo2213/adventofcode/y2020/day${day2D}"
    file="${dir}/Day${day2D}${1}.java"
    content="package io.github.joblo2213.adventofcode.y2020.day${day2D};

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

public class Day${day2D}${1} extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        //TODO Implement
        return -1;
    }
}
"
    mkFile "${dir}" "${file}" "${content}"
}

# Make a new unit test file
# param $1 - A or B
# shellcheck disable=SC2089
function mkTestFile() {
    dir="src/test/java/io/github/joblo2213/adventofcode/y2020/day${day2D}"
    file="${dir}/Day${day2D}${1}_Test.java"
    content="package io.github.joblo2213.adventofcode.y2020.day${day2D};

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

public class Day${day2D}${1}_Test {

    @Test
    @DisabledIfEnvironmentVariable(named = \"CI\", matches = \"true\", disabledReason = \"Disabled on GH Actions\")
    public void task() throws Exception {
        new Day${day2D}${1}().run();
    }
}
"
    mkFile "${dir}" "${file}" "${content}"
}

echo "${AOC_SESSION}"

# Download puzzle input file
function mkInputFile() {
    dir="src/main/resources/io/github/joblo2213/adventofcode/y2020/day${day2D}"
    file="${dir}/puzzle_input.txt"
    url="https://adventofcode.com/2020/day/${day}/input"
    if [ -f "${file}" ]; then
      echo "File already exists: ${file}"
    else
      mkdir -p "${dir}"
      wget --header="Cookie: session=${AOC_SESSION}" -O "${file}" "${url}"
      echo "Downloaded: ${file}"
    fi
}

mkTaskFile "A"
mkTestFile "A"
mkTaskFile "B"
mkTestFile "B"
mkInputFile