package day3;

import util.AocUtil;
import util.DayInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 implements DayInterface {

    private static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    private static final String upperCase = lowerCase.toUpperCase();
    private static final String priorityString = lowerCase + upperCase;

    @Override
    public String puzzle1(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        int totalPriority = 0;
        for (String rucksack : lines) {
            int rucksackLength = rucksack.length();
            String firstCompartment = rucksack.substring(0, rucksackLength / 2);
            String secondCompartment = rucksack.substring(rucksackLength / 2);

            for (int i = 0; i < firstCompartment.length(); i++) {
                char item1 = firstCompartment.charAt(i);

                if (secondCompartment.indexOf(item1) != -1) {
                    int priority = priorityString.indexOf(item1) + 1;
                    totalPriority += priority;
                    break;
                }
            }
        }

        return Integer.toString(totalPriority);
    }

    @Override
    public String puzzle2(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        int totalBadgeScore = 0;
        for (int i = 0; i < lines.size(); i += 3) {
            final List<Character> rucksack1 = lines.get(i).chars().mapToObj(c -> (char) c).toList();
            final List<Character> rucksack2 = lines.get(i + 1).chars().mapToObj(c -> (char) c).toList();
            final List<Character> rucksack3 = lines.get(i + 2).chars().mapToObj(c -> (char) c).toList();

            List<Character> longestRucksack = Stream
                    .of(rucksack1, rucksack2, rucksack3)
                    .max(Comparator.comparing(List::size))
                    .orElseThrow();

            Character groupBadge = longestRucksack.stream()
                    .filter(item -> rucksack1.contains(item) && rucksack2.contains(item) && rucksack3.contains(item))
                    .distinct()
                    .findFirst()
                    .orElseThrow();

            int priority = priorityString.indexOf(groupBadge) + 1;
            totalBadgeScore += priority;
        }
        return Integer.toString(totalBadgeScore);
    }
}
