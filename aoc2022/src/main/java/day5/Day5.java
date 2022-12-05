package day5;

import lombok.AllArgsConstructor;
import util.AocUtil;
import util.DayInterface;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

            int stackCounter = 1;
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


        // Reverse stack
        stackMap.entrySet().forEach(integerStackEntry -> {
            Stack<Character> originalOrderedStack = integerStackEntry.getValue();
            Stack<Character> correctOrderedStack = new Stack<>();


            while(!originalOrderedStack.empty()){
                correctOrderedStack.push(originalOrderedStack.pop());
            }

            integerStackEntry.setValue(correctOrderedStack);
        });

        int firstMovementLineIndex = 0;

        for (String line : lines) {
            if (line.length() != 0) {
                firstMovementLineIndex++;
            } else {
                break;
            }
        }

        lines.stream()
                .skip(firstMovementLineIndex + 1)
                .map(Movement::new)
                .forEachOrdered(movement -> {
                    for (int i = 0; i < movement.crateCount; i++) {
                        Character movableCrate = stackMap.get(movement.fromStack).pop();
                        stackMap.get(movement.toStack).push(movableCrate);
                    };
                });

        return stackMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(integerStackEntry -> integerStackEntry.getValue().peek())
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    @Override
    public String puzzle2(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        Map<Integer, Stack<Character>> stackMap = new HashMap<>();

        // Establish stacks of crates
        for (String line : lines) {
            if (!line.contains("[")) {
                continue;
            }

            int stackCounter = 1;
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


        // Reverse stack
        stackMap.entrySet().forEach(integerStackEntry -> {
            Stack<Character> originalOrderedStack = integerStackEntry.getValue();
            Stack<Character> correctOrderedStack = new Stack<>();


            while(!originalOrderedStack.empty()){
                correctOrderedStack.push(originalOrderedStack.pop());
            }

            integerStackEntry.setValue(correctOrderedStack);
        });

        int firstMovementLineIndex = 0;

        for (String line : lines) {
            if (line.length() != 0) {
                firstMovementLineIndex++;
            } else {
                break;
            }
        }

        lines.stream()
            .skip(firstMovementLineIndex + 1)
            .map(Movement::new)
            .forEachOrdered(movement -> {

                List<Character> addable = new ArrayList<>();

                for (int i = 0; i < movement.crateCount; i++) {
                    Character movableCrate = stackMap.get(movement.fromStack).pop();
                    addable.add(movableCrate);
                }

                Collections.reverse(addable);
                stackMap.get(movement.toStack).addAll(addable);
            });

        return stackMap.entrySet()
            .stream()
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .map(integerStackEntry -> integerStackEntry.getValue().peek())
            .map(String::valueOf)
            .collect(Collectors.joining(""));
    }

    class Movement {
        private int fromStack;
        private int toStack;
        private int crateCount;

        Movement(String input) {
            Pattern pat = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            Matcher matcher = pat.matcher(input);

            if (matcher.find()) {
                this.crateCount = Integer.parseInt(matcher.group(1));
                this.fromStack = Integer.parseInt(matcher.group(2));
                this.toStack = Integer.parseInt(matcher.group(3));
            } else {
                throw new IllegalArgumentException();
            }

        }
    }
}
