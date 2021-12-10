package adventofcode.day10;

import adventofcode.util.AocUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    public int part1() {
        List<LogLine> lineStatuses = AocUtils.getLinesFromFile("src/main/resources/day10/sampleinput.txt")
                .stream()
                .map(LogLine::new)
                .collect(Collectors.toList());

        return lineStatuses.stream()
                .filter(logLine -> logLine.getLineStatusEnum() == LineStatusEnum.CORRUPT)
                .mapToInt(logLine -> ChunkUtils.endingCharToCompletionScoreMap.get(logLine.getFoundUnexpectedChar()))
                .sum();
    }

    public long part2() {
        List<LogLine> completedLogLines = AocUtils.getLinesFromFile("src/main/resources/day10/actualinput.txt")
                .stream()
                .map(LogLine::new)
                .filter(logLine -> logLine.getLineStatusEnum() == LineStatusEnum.INCOMPLETE)
                .collect(Collectors.toList());

        completedLogLines.forEach(LogLine::completeChunk);

        int middleIndex = completedLogLines.size() / 2;
        return completedLogLines.stream()
                .sorted(Comparator.comparingLong(LogLine::getCompletionScore))
                .collect(Collectors.toList())
                .get(middleIndex)
                .getCompletionScore();
    }
}
