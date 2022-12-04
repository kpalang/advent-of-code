package adventofcode.day8;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DisplaySegment {
    /**
     * Segments definition:
     *  1111
     * 7    2
     * 7    2
     *  6666
     * 5    3
     * 5    3
     *  4444
     */

    @Getter private Map<Integer, String> segmentToStringMap;

    @Getter private int value;

    public DisplaySegment(List<String> inputData) {
        segmentToStringMap = new HashMap<>();
        IntStream.range(1, 8).forEach(segmentNumber -> segmentToStringMap.put(segmentNumber, "."));

        // Get number 1
        String oneString = inputData.stream().filter(s -> s.length() == 2).findFirst().get();
        List<String> oneList = oneString.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.toList());

        // Get number 4
        String fourString = inputData.stream().filter(s -> s.length() == 5).findFirst().get();
        List<String> fourList = fourString.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.toList());

        // Get number 7
        String sevenString = inputData.stream().filter(s -> s.length() == 3).findFirst().get();
        List<String> sevenList = sevenString.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.toList());

        sevenList.removeAll(oneList);
        segmentToStringMap.put(1, sevenList.get(0));

        /*
        // Get number 8
        String eightString = inputData.stream().filter(s -> s.length() == 8).findFirst().get();
        List<String> eightList = eightString.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.toList());

         */


    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();

        String segmentCharacter1 = segmentToStringMap.get(1);
        String segmentCharacter2 = segmentToStringMap.get(1);
        stringBuilder.append(String.format(" %1$s%1$s%1$s%1$s %n", segmentCharacter1));

        segmentCharacter1 = segmentToStringMap.get(7);
        segmentCharacter2 = segmentToStringMap.get(2);
        stringBuilder.append(String.format("%s    %s%n", segmentCharacter1, segmentCharacter1));
        stringBuilder.append(String.format("%s    %s%n", segmentCharacter1, segmentCharacter1));

        segmentCharacter1 = segmentToStringMap.get(6);
        stringBuilder.append(String.format(" %1$s%1$s%1$s%1$s %n", segmentCharacter1));

        segmentCharacter1 = segmentToStringMap.get(5);
        segmentCharacter2 = segmentToStringMap.get(3);
        stringBuilder.append(String.format("%s    %s%n", segmentCharacter1, segmentCharacter1));
        stringBuilder.append(String.format("%s    %s%n", segmentCharacter1, segmentCharacter1));

        segmentCharacter1 = segmentToStringMap.get(4);
        stringBuilder.append(String.format(" %1$s%1$s%1$s%1$s %n", segmentCharacter1));


        return stringBuilder.toString();
    }
}
