package adventofcode.day11;

import adventofcode.day9.HeightCoordinate;
import adventofcode.util.AocUtils;
import adventofcode.util.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 {
    public int part1() {
        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day11/sampleinput.txt");
        Integer[][] octopusMap = getOctopusMap(lines);

        System.out.println(AocUtils.print2dArray(octopusMap));

        int totalFlashes = 0;
        int firstStepToFullFlash;
        for (int step = 0; step < 500; step++) {

            octopusMap = increaseOctopusMap(octopusMap);

            boolean anyOctopusesLeftToFlash = true;

            while (anyOctopusesLeftToFlash) {
                anyOctopusesLeftToFlash = false;

                // Flash any octopuses
                for (int y = 0; y < octopusMap.length; y++) {
                    for (int x = 0; x < octopusMap[0].length; x++) {
                        Octopus octopus = new Octopus(x, y, octopusMap[y][x]);
                        if (octopus.getEnergy() <= 9) continue;

                        Set<Coordinate> adjacentOctopuses = getAdjacentCoordinates(octopusMap, octopus);

                        for (Coordinate coordinate : adjacentOctopuses) {
                            if (octopusMap[coordinate.getPosY()][coordinate.getPosX()] == -1) continue;

                            octopusMap[coordinate.getPosY()][coordinate.getPosX()] += 1;
                        }

                        octopusMap[y][x] = -1;
                        totalFlashes++;
                    }
                }

                boolean breakCheck = false;
                // Check if any octopuses left to flash
                for (int y = 0; y < octopusMap.length; y++) {
                    if (breakCheck) break;

                    for (int x = 0; x < octopusMap[0].length; x++) {
                        if (octopusMap[y][x] > 9) {
                            breakCheck = true;
                            anyOctopusesLeftToFlash = true;
                            break;
                        }
                    }
                }

            }

            if (isFullFlash(octopusMap)) {
                System.out.println("Full flash at step " + (step + 1));
            }

            // Reset octopuses to be able to flash
            octopusMap = setOctopusesAvailableToFlash(octopusMap);

            //System.out.println("---");
            //System.out.println("After step " + (step + 1));
            //System.out.println(AocUtils.print2dArray(octopusMap));

        }


        return totalFlashes;
    }

    public int part2() {

        return 0;
    }

    private Integer[][] increaseOctopusMap(Integer[][] map) {
        Integer[][] result = map.clone();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                result[y][x] += 1;
            }
        }

        return result;
    }

    private Integer[][] setOctopusesAvailableToFlash(Integer[][] map) {
        Integer[][] result = map.clone();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (result[y][x] != -1) continue;
                result[y][x] = 0;
            }
        }

        return result;
    }

    private boolean isFullFlash(Integer[][] map) {

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] != -1) return false;
            }
        }

        return true;
    }

    private Set<Coordinate> getAdjacentCoordinates(Integer[][] map, Coordinate origin) {

        List<Coordinate> adjacentCoordinatesToTest = new ArrayList<>();
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                if (x == 0 && y == 0) continue;
                adjacentCoordinatesToTest.add(new Coordinate(origin.getPosX() + x, origin.getPosY() + y));
            }
        }

        return adjacentCoordinatesToTest.stream()
                .filter(coordinate -> AocUtils.isCoordinateOn2dArray(map, coordinate))
                .collect(Collectors.toSet());
    }

    private Integer[][] getOctopusMap(List<String> lines) {
        // Rows of columns
        Integer[][] heightMap = new Integer[10][10];

        IntStream.range(0, 10).forEach(rowIndex -> {
            String line = lines.get(rowIndex);

            IntStream.range(0, 10).forEach(columnIndex -> {
                heightMap[rowIndex][columnIndex] = Integer.parseInt(String.valueOf(line.charAt(columnIndex)));
            });
        });

        return heightMap;
    }

}
