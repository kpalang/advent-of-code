package adventofcode.day1;

import adventofcode.util.AocUtils;

import java.util.LinkedList;
import java.util.List;

public class Day1 {
    public int part1() {
        int increasedDepthCount = -1;
        int previousDepth = -1;

        for (Integer currentDepth : getListOfDepths()) {
            if (previousDepth < currentDepth) {
                increasedDepthCount++;
            }
            previousDepth = currentDepth;
        }
        return increasedDepthCount;
    }

    public int part2() {
        LinkedList<Integer> depths = (LinkedList<Integer>) getListOfDepths();

        int previousWindowSum = 0;
        int increasedDepthCount = 0;
        for (int i = 0; i < depths.size() - 3; i++) {
            int currentWindowSum = depths.get(i) + depths.get(i + 1) + depths.get(i + 2);

            if (previousWindowSum < currentWindowSum) {
                increasedDepthCount++;
            }
            previousWindowSum = currentWindowSum;
        }

        return increasedDepthCount;
    }

    private List<Integer> getListOfDepths() {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<String> lines = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day1/sonarinput.txt");
        lines.forEach(line -> {
            if (line.trim().length() != 0) {
                result.add(Integer.parseInt(line));
            }
        });

        return result;
    }
}
