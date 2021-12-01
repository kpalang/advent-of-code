package adventofcode.day1;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
        try (
                FileInputStream fileInputStream = new FileInputStream("src/main/resources/day1/sonarinput.txt");
                Scanner sc = new Scanner(fileInputStream)
        ) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                if (currentLine.trim().length() == 0) {
                    break;
                }

                result.add(Integer.parseInt(currentLine));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
