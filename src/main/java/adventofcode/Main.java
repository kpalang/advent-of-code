package adventofcode;

import adventofcode.day1.Day1;
import adventofcode.day2.Day2;
import adventofcode.day3.Day3;

public class Main {
    public static void main(String[] args) {
        //Day1 day1 = new Day1();
        //System.out.println("Advent of code 2021: Day 1");
        //System.out.printf("Part1 -> Depth increased %s times%n", day1.part1());
        //System.out.printf("Part2 -> Depth increased %s times%n", day1.part2());

        //Day2 day2 = new Day2();
        //System.out.println("--------------------------");
        //System.out.println("Advent of code 2021: Day 2");
        //System.out.printf("Part1 -> The submarine's position is %s%n", day2.part1());
        //System.out.printf("Part2 -> The submarine's position is %s%n", day2.part2());

        Day3 day3 = new Day3();
        System.out.println("--------------------------");
        System.out.println("Advent of code 2021: Day 3");
        System.out.printf("Part1 -> The submarine's power consumption is %s%n", day3.part1());
        System.out.printf("Part2 -> The submarine's life support rating is %s%n", day3.part2());
    }
}
