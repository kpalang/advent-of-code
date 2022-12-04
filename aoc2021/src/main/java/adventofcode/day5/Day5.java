package adventofcode.day5;

import adventofcode.util.AocUtils;
import adventofcode.util.Direction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {
    public long part1() {
        List<Vent> ventList = AocUtils.getLinesFromFile("src/main/resources/day5/sampleinput.txt")
                .stream()
                .map(Vent::new)
                .filter(vent -> vent.getDirection() != Direction.DIAGONAL_RISING)
                .filter(vent -> vent.getDirection() != Direction.DIAGONAL_FALLING)
                .collect(Collectors.toList());

        return doWork(ventList);
    }

    public long part2() {
        List<Vent> ventList = AocUtils.getLinesFromFile("src/main/resources/day5/sampleinput.txt")
                .stream()
                .map(Vent::new)
                .collect(Collectors.toList());

        return doWork(ventList);
    }

    private int doWork(List<Vent> ventList) {
        int maxRowIndex = ventList.stream()
                .max(Comparator.comparingInt(vent -> vent.getEndCoordinate().getPosY()))
                .get()
                .getEndCoordinate()
                .getPosY();

        int maxColumnIndex = ventList.stream()
                .max(Comparator.comparingInt(vent -> vent.getEndCoordinate().getPosX()))
                .get()
                .getEndCoordinate()
                .getPosX();

        Integer[][] ventMap = generateVentMap(maxRowIndex, maxColumnIndex);

        ventList.forEach(vent -> {
            Direction direction = vent.getDirection();

            if (direction == Direction.HORIZONTAL) {
                for (int i = vent.getStartCoordinate().getPosX(); i <= vent.getEndCoordinate().getPosX(); i++) {
                    ventMap[vent.getStartCoordinate().getPosY()][i] += 1;
                }
            } else if (direction == Direction.VERTICAL) {
                for (int i = vent.getStartCoordinate().getPosY(); i <= vent.getEndCoordinate().getPosY(); i++) {
                    ventMap[i][vent.getStartCoordinate().getPosX()] += 1;
                }
            } else {

                if (direction == Direction.DIAGONAL_RISING) {
                    for (int i = vent.getStartCoordinate().getPosX(); i <= vent.getEndCoordinate().getPosX(); i++) {
                        ventMap[vent.getStartCoordinate().getPosY()][i] += 1;
                    }
                } else {
                    for (int i = vent.getStartCoordinate().getPosX(); i <= vent.getEndCoordinate().getPosX(); i--) {
                        ventMap[i][i] += 1;
                    }
                }
            }
        });

        System.out.println(print2dArray(ventMap));

        int totalOverlappingPoints = 0;

        for (int row = 0; row <= maxRowIndex; row++) {
            for (int column = 0; column <= maxColumnIndex; column++) {
                int currentLineCount = ventMap[row][column];

                if (currentLineCount > 1) totalOverlappingPoints++;
            }
        }

        return totalOverlappingPoints;
    }

    private Integer[][] generateVentMap(int rowCount, int columnCount) {
        // Rows of columns
        Integer[][] ventMap = new Integer[rowCount + 1][columnCount + 1];

        IntStream.rangeClosed(0, rowCount).forEach(rowIndex -> {
            IntStream.rangeClosed(0, columnCount).forEach(columnIndex -> {
                ventMap[rowIndex][columnIndex] = 0;
            });
        });

        return ventMap;
    }

    private static String print2dArray(Object[][] array2d) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int rowIndex = 0; rowIndex < array2d.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < array2d[rowIndex].length; columnIndex++) {
                Object value = array2d[rowIndex][columnIndex];

                if ((Integer) value == 0) value = "_";

                stringBuilder.append(value).append(" | ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
