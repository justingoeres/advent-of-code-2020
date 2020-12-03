#!/usr/bin/env bash

# A best practices Bash script template with many useful functions. This file
# sources in the bulk of the functions from the source.sh file which it expects
# to be in the same directory. Only those functions which are likely to need
# modification are present in this file. This is a great combination if you're
# writing several scripts! By pulling in the common functions you'll minimise
# code duplication, as well as ease any potential updates to shared functions.

# Enable xtrace if the DEBUG environment variable is set
if [[ ${DEBUG-} =~ ^1|yes|true$ ]]; then
    set -o xtrace       # Trace the execution of the script (debug)
fi

# A better class of script...
set -o errexit          # Exit on most errors (see the manual)
set -o errtrace         # Make sure any error trap is inherited
set -o nounset          # Disallow expansion of unset variables
set -o pipefail         # Use last non-zero exit code in a pipeline

# DESC: Usage help
# ARGS: None
# OUTS: None
function script_usage() {
    cat << EOF
Usage:
     -d|--day                   Specifies the AoC day # to create
     -h|--help                  Displays this help
     -v|--verbose               Displays verbose output
    -nc|--no-colour             Disables colour output
    -cr|--cron                  Run silently unless we encounter an error
EOF
}

# DESC: Parameter parser
# ARGS: $@ (optional): Arguments provided to the script
# OUTS: Variables indicating command-line parameters and options
function parse_params() {
    local param
    while [[ $# -gt 0 ]]; do
        param="$1"
        shift
        case $param in
            -d | --day)
                raw_day="$1"    # Pretty version of the day for e.g. git commits
                day="$1"
                # Add leading zero if necessary
                if [ "$day" -lt "10" ]; then
                    day=0$day
                fi
                shift   # consume the number after -d
                ;;
            -h | --help)
                script_usage
                exit 0
                ;;
            -v | --verbose)
                verbose=true
                ;;
            -nc | --no-colour)
                no_colour=true
                ;;
            -cr | --cron)
                cron=true
                ;;
            *)
                script_exit "Invalid parameter was provided: $param" 1
                ;;
        esac
    done
}

function instantiateTemplate() {
    sourceFile="$1"
    targetFile="$2"
    targetDir=$( dirname "$2" )

    pretty_print "Instantiating $(basename "$sourceFile") as $(basename "$outputFile1")" $fg_cyan$ta_bold

    # Make sure the targetDir exists
    mkdir -p $targetDir

    # Replace all instances of ${AOC_DAY} with $day
    # and write the modified file out to $targetFile
    sed "s/\${AOC_DAY}/$day/g" $sourceFile > $targetFile
}

function get_problem_input() {
    pretty_print "Downloading puzzle input for Day $raw_day" $fg_magenta$ta_bold

    mkdir -p $( dirname "$puzzleInputFile" )
    resources/aocdl -year $YEAR -day $day -output "$puzzleInputFile"
}

# DESC: Main control flow
# ARGS: $@ (optional): Arguments provided to the script
# OUTS: None
function main() {
    # shellcheck source=source.sh
    source "$(dirname "${BASH_SOURCE[0]}")/resources/source.sh"

    trap script_trap_err ERR
    trap script_trap_exit EXIT

    script_init "$@"
    parse_params "$@"
    cron_init
    colour_init
    #lock_init system

    YEAR=2020
    puzzleInputFile="data/day$day/input.txt"
    srcDir=src/main/java/org/jgoeres/adventofcode$YEAR/Day$day
    testDir=src/test/java/org/jgoeres/adventofcode$YEAR
    templateDir=resources/templates 

    # Get the puzzle input
    get_problem_input
    
    # RunDayXXTemplate.java
    templateFile1=RunDayXXTemplate.java
    outputFile1=${templateFile1/XXTemplate/$day}  # e.g. RunDayXXTemplate.java -> RunDay01Template.java
    instantiateTemplate "$templateDir/$templateFile1" "$srcDir/$outputFile1"

    # DayXXServiceTemplate.java
    templateFile2=DayXXServiceTemplate.java
    outputFile2=${templateFile2/XXServiceTemplate/"$day"Service}  # e.g. RunDayXXTemplate.java -> RunDay01Template.java
    instantiateTemplate "$templateDir/$templateFile2" "$srcDir/$outputFile2"

    # DayXXTestTemplate.java
    templateFile3=DayXXTestTemplate.java
    outputFile3=${templateFile3/XXTestTemplate/"$day"Test}  # e.g. RunDayXXTemplate.java -> RunDay01Template.java
    instantiateTemplate "$templateDir/$templateFile3" "$testDir/$outputFile3"

    # Commit everything
    pretty_print "Committing..." $fg_yellow$ta_bold
    git add "$puzzleInputFile" "$srcDir/$outputFile1" "$srcDir/$outputFile2" "$testDir/$outputFile3"
    git commit -m "Day $raw_day Init from aocInit.sh"

}

# Make it rain
main "$@"

# vim: syntax=sh cc=80 tw=79 ts=4 sw=4 sts=4 et sr
