package adventofcode.day6;

import adventofcode.util.AocUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {

    public int part1() {

        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day6/sampleinput.txt");

        List<Integer> stateOfFishPool = Lists.newArrayList(Splitter.on(",").split(lines.get(0)))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int daysToProcess = 1;

        for (int day = 0; day < daysToProcess; day++) {

            for (int fishIndex = 0; fishIndex < stateOfFishPool.size(); fishIndex++) {
                int fishDay = stateOfFishPool.get(fishIndex);

                boolean makeFishBabi = false;
                if (0 < fishDay) {
                    fishDay -= 1;
                } else {
                    fishDay = 6;
                    makeFishBabi = true;
                }

                stateOfFishPool.set(fishIndex, fishDay);
                if (makeFishBabi) stateOfFishPool.add(9);
            }
        }

        return stateOfFishPool.size();
    }

    public long part2() {
        // https://www.reddit.com/r/adventofcode/comments/r9z49j/comment/hnfy04x

        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day6/fishinput.txt");

        Map<Integer, Long> stateOfFishPool = Arrays.stream(lines.get(0).trim().split(","))
                .collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()));

        // IntStream.range() is a way fancier for loop

        IntStream.range(0, 9).forEach(value -> stateOfFishPool.putIfAbsent(value, 0L));
        IntStream.range(0, 256).forEach(day -> stateOfFishPool.compute((day + 7) % 9, (fishDay, fishCount) -> fishCount + stateOfFishPool.get(day % 9)));

        return stateOfFishPool.values().stream().mapToLong(i->i).sum();
    }
}
