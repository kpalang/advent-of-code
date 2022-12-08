package day6;

import util.AocUtil;
import util.DayInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day6 implements DayInterface {
    @Override
    public String puzzle1(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);
        final String dataStream = lines.get(0);

        List<Character> lastFourChars = new ArrayList<>();

        char[] dataStreamCharArray = dataStream.toCharArray();

        int markerIndex = -1;
        for (int i = 0; i < dataStreamCharArray.length; i++) {

            char chr = dataStreamCharArray[i];

            if (lastFourChars.size() >= 4) {
                lastFourChars.remove(0);
            }

            lastFourChars.add(chr);

            if (lastFourChars.stream().distinct().count() == 4) {
                markerIndex = i + 1;
                break;
            }
        }

        return String.valueOf(markerIndex);
    }

    @Override
    public String puzzle2(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);
        final String dataStream = lines.get(0);

        List<Character> lastFourChars = new ArrayList<>();

        char[] dataStreamCharArray = dataStream.toCharArray();

        int markerIndex = -1;
        for (int i = 0; i < dataStreamCharArray.length; i++) {

            char chr = dataStreamCharArray[i];

            if (lastFourChars.size() >= 14) {
                lastFourChars.remove(0);
            }

            lastFourChars.add(chr);

            if (lastFourChars.stream().distinct().count() == 1) {
                markerIndex = i + 1;
                break;
            }
        }

        return String.valueOf(markerIndex);
    }
}
