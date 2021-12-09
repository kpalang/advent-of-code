package adventofcode.day7;

import adventofcode.util.AocUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Day7 {
    public long part1() {
        Map<Integer, Long> crabsOnPositions = Arrays.stream(
                    AocUtils.getLinesFromFile("src/main/resources/day7/sampleinput.txt").get(0).trim().split(",")
                )
                .collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()));

        System.out.println(crabsOnPositions);

        int crabPositionsMode = Collections.max(crabsOnPositions.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();

        AtomicLong totalFuelConsumption = new AtomicLong(0L);
        crabsOnPositions.forEach((position, crabCount) -> {
            long fuelCostFromCurrentPosition = Math.max(position, crabPositionsMode) - Math.min(position, crabPositionsMode);
            long totalFuelCostFromCurrentPosition = fuelCostFromCurrentPosition * crabCount;
            totalFuelConsumption.addAndGet(totalFuelCostFromCurrentPosition);
        });

        return totalFuelConsumption.get();
    }

    public int part2() {

        return 0;
    }
}
