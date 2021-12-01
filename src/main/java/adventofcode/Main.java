package adventofcode;

import adventofcode.day1.Day1;

public class Main {
    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.println("Advent of code 2021: Day 1");
        System.out.printf("Part1 -> Depth increased %s times%n", day1.part1());
        System.out.printf("Part2 -> Depth increased %s times%n", day1.part2());
    }
}
