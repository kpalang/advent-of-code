package adventofcode.day3;

import adventofcode.util.AocUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    public int part1() {
        LinkedList<String> lines = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day3/diagnosticinput.txt");
        //LinkedList<String> lines = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day3/sampleinput.txt");

        StringBuilder gammaRate = new StringBuilder(); // most common bits
        StringBuilder epsilonRate = new StringBuilder(); // least common bits

        for (Map<Integer, Integer> map : getListOfBitCounts(lines)) {

            Integer gammaKey = Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
            Integer epsilonKey = Collections.min(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

            gammaRate.append(gammaKey);
            epsilonRate.append(epsilonKey);
        }

        int gammaDecimal = Integer.parseInt(gammaRate.toString(), 2);
        int epsilonDecimal = Integer.parseInt(epsilonRate.toString(), 2);

        return gammaDecimal * epsilonDecimal;
    }

    public int part2() {
        LinkedList<String> lines = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day3/diagnosticinput.txt");
        //LinkedList<String> lines = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day3/sampleinput.txt");

        List<String> oxygenLinesToModify = List.copyOf(lines);
        List<String> carbonLinesToModify = List.copyOf(lines);

        for (int i = 0; i < lines.get(0).length(); i++) {
            if (oxygenLinesToModify.size() > 1) {
                oxygenLinesToModify = doTheThing(oxygenLinesToModify, i, 1, true);
            }

            if (carbonLinesToModify.size() > 1) {
                carbonLinesToModify = doTheThing(carbonLinesToModify, i, 0, false);
            }
        }

        int oxygenRating = Integer.parseInt(oxygenLinesToModify.get(0), 2);
        int carbonRating = Integer.parseInt(carbonLinesToModify.get(0), 2);

        return oxygenRating * carbonRating;
    }

    private List<String> doTheThing(List<String> listOfLines, int index, int defaultBit, boolean useMostCommonBit) {
        Map<Integer, Integer> map = getListOfBitCounts(listOfLines).get(index);

        Integer bitToUse;
        if (Objects.equals(map.get(0), map.get(1))) {
            bitToUse = defaultBit;
        } else if (useMostCommonBit) {
            bitToUse = Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        } else {
            bitToUse = Collections.min(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        }

        final int finalBitToUse = bitToUse;
        return listOfLines
                .stream()
                .filter(line -> Character.getNumericValue(line.charAt(index)) == finalBitToUse)
                .collect(Collectors.toList());
    }

    private List<Map<Integer, Integer>> getListOfBitCounts(List<String> lines) {
        List<Map<Integer, Integer>> listOfMapBitCounts = new ArrayList<>();

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                Map<Integer, Integer> valueMap = new HashMap<>();
                valueMap.put(0, 0);
                valueMap.put(1, 0);

                if (listOfMapBitCounts.size() <= i) {
                    listOfMapBitCounts.add(i, valueMap);
                } else {
                    valueMap = listOfMapBitCounts.get(i);
                }

                int currentKey = Character.getNumericValue(line.charAt(i));
                int incrementedValue = valueMap.get(currentKey) + 1;

                valueMap.put(currentKey, incrementedValue);

                listOfMapBitCounts.set(i, valueMap);
            }
        }

        return listOfMapBitCounts;
    }
}
