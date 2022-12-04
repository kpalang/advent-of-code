package day1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import util.AocUtil;
import util.DayInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class Day1 implements DayInterface {

    public String puzzle1(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        List<List<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>());

        List<List<String>> result = lines.stream().reduce(initial, (subtotal, element) -> {
            if (element.trim().isEmpty()) {
                subtotal.add(new ArrayList<>());
            } else {
                subtotal.get(subtotal.size() - 1).add(element);
            }
            return subtotal;

        }, (list1, list2) -> emptyList());

        return result
            .stream()
            .map(singleElf -> singleElf
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .sum()
            ).sorted(Comparator.reverseOrder())
            .map(integer -> Integer.toString(integer))
            .findFirst()
            .orElseThrow();
    }

    public String puzzle2(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        List<List<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>());

        List<List<String>> result = lines.stream().reduce(initial, (subtotal, element) -> {
            if (element.trim().isEmpty()) {
                subtotal.add(new ArrayList<>());
            } else {
                subtotal.get(subtotal.size() - 1).add(element);
            }
            return subtotal;

        }, (list1, list2) -> emptyList());

        int sum = result
                .stream()
                .map(singleElf -> singleElf
                        .stream()
                        .mapToInt(Integer::parseInt)
                        .sum()
                ).sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();

        return Integer.toString(sum);
    }
}
