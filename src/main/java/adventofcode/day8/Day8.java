package adventofcode.day8;

import adventofcode.util.AocUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day8 {
    public int part1() {

        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day8/sampleinput.txt");

        String[] split = lines.get(0).split(" \\| ");

        String inputData = split[0].trim();
        String outputData = split[1].trim();

        List<String> segmentsData = Arrays.stream(inputData.split(" "))
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        DisplaySegment displaySegment = new DisplaySegment(segmentsData);

        return 0;
    }

    public int part2() {

        return 0;
    }
}
