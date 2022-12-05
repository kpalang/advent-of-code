package day5;

import util.AocUtil;
import util.DayInterface;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Day5 implements DayInterface {
    @Override
    public String puzzle1(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        Map<Integer, Stack<Character>> stackMap = new HashMap<>();

        // Establish stacks of crates
        for (String line : lines) {
            if (!line.contains("[")) {
                continue;
            }

            int stackCounter = 0;
            for (int i = 0; i < line.length() - 2; i += 3) {
                char crateChar = line.charAt(i + 1);

                if (crateChar != 32) {

                    if (!stackMap.containsKey(stackCounter)) {
                        stackMap.put(stackCounter, new Stack<>());
                    }

                    stackMap.get(stackCounter).push(crateChar);
                }

                stackCounter++;

                if (line.length() > i + 1) {
                    i++;
                }
            }
        }

        return null;
    }

    @Override
    public String puzzle2(String inputPath) {
        return null;
    }
}
