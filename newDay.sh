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
    dir="src/main/java/io/github/joblo2213/adventofcode/y2021/day${day2D}"
    file="${dir}/Day${day2D}${1}.java"
    content=$(day2D=${day2D} ch=${1} envsubst < "templates/Task.java")
    mkFile "${dir}" "${file}" "${content}"
}

# Make a new unit test file
# param $1 - A or B
# shellcheck disable=SC2089
function mkTestFile() {
    dir="src/test/java/io/github/joblo2213/adventofcode/y2021/day${day2D}"
    file="${dir}/Day${day2D}${1}_Test.java"
    content=$(day2D=${day2D} ch=${1} envsubst < "templates/Test.java")
    mkFile "${dir}" "${file}" "${content}"
}

echo "${AOC_SESSION}"

# Download puzzle input file
function mkInputFile() {
    dir="src/main/resources/io/github/joblo2213/adventofcode/y2021/day${day2D}"
    file="${dir}/puzzle_input.txt"
    url="https://adventofcode.com/2021/day/${day}/input"
    if [ -f "${file}" ]; then
      echo "File already exists: ${file}"
    else
      mkdir -p "${dir}"
      if wget --header="Cookie: session=${AOC_SESSION}" -O "${file}" "${url}"; then
        echo "Downloaded: ${file}"
        return 0
      else
        echo "Download failed"
        return 1
      fi
    fi
}

mkTaskFile "A"
mkTestFile "A"
mkTaskFile "B"
mkTestFile "B"
if mkInputFile; then
  echo "Created template. Head over to https://adventofcode.com/2021/day/${day} for the puzzle!"
fi