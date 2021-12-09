package adventofcode.day9;

import adventofcode.util.AocUtils;
import adventofcode.util.Coordinate;
import com.google.common.collect.Range;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day9 {
    public int part1() {
        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day9/actualinput.txt");
        Integer[][] heightMap = getHeightMap(lines);
        List<HeightCoordinate> lowCoordinates = getLowPoints(heightMap);

        // Get sum of risk levels
        return lowCoordinates.stream()
                .mapToInt(heightCoordinate -> heightCoordinate.getHeight() + 1)
                .sum();
    }

    public int part2() {
        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day9/actualinput.txt");

        Integer[][] heightMap = getHeightMap(lines);
        List<HeightCoordinate> lowCoordinates = getLowPoints(heightMap);

        return lowCoordinates.stream()
                .map(heightCoordinate -> getAdjacentCoordinates(heightMap, heightCoordinate, Stream.of(heightCoordinate).collect(Collectors.toCollection(HashSet::new))))
                .mapToInt(Set::size)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (subtotal, element) -> subtotal * element);
    }

    private Set<Coordinate> getAdjacentCoordinates(Integer[][] map, Coordinate origin, Set<Coordinate> knownCoordinates) {

        List<Coordinate> adjacentCoordinatesToTest = new ArrayList<>();
        for (int y = -1; y < 2; y += 2) {
            adjacentCoordinatesToTest.add(new Coordinate(origin.getPosX(), origin.getPosY() + y));
        }
        for (int x = -1; x < 2; x += 2) {
            adjacentCoordinatesToTest.add(new Coordinate(origin.getPosX() + x, origin.getPosY()));
        }

        Set<Coordinate> currentAdjacentCoordinates = adjacentCoordinatesToTest.stream()
                .filter(coordinate -> !knownCoordinates.contains(coordinate))
                .filter(coordinate -> AocUtils.isCoordinateOn2dArray(map, coordinate))
                .filter(coordinate -> map[coordinate.getPosY()][coordinate.getPosX()] != 9)
                .collect(Collectors.toSet());

        if (currentAdjacentCoordinates.isEmpty()) return new HashSet<>();

        knownCoordinates.addAll(currentAdjacentCoordinates);

        for (Coordinate coordinate : currentAdjacentCoordinates) {
            getAdjacentCoordinates(map, coordinate, knownCoordinates);
        }

        return knownCoordinates;
    }

    private Integer[][] getHeightMap(List<String> lines) {
        int rowCount = lines.size();
        int columnCount = lines.get(0).length();

        // Rows of columns
        Integer[][] heightMap = new Integer[rowCount][columnCount];

        IntStream.range(0, lines.size()).forEach(rowIndex -> {
            String line = lines.get(rowIndex);

            IntStream.range(0, line.length()).forEach(columnIndex -> {
                heightMap[rowIndex][columnIndex] = Integer.parseInt(String.valueOf(line.charAt(columnIndex)));
            });
        });

        return heightMap;
    }

    private List<HeightCoordinate> getLowPoints(Integer[][] heightMap) {
        List<HeightCoordinate> lowCoordinates = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < heightMap.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < heightMap[rowIndex].length; columnIndex++) {
                Optional<Integer> northHeight =
                        Range.closedOpen(0, heightMap.length).contains(rowIndex - 1) ?
                                Optional.of(heightMap[rowIndex - 1][columnIndex]) :
                                Optional.empty();

                Optional<Integer> southHeight =
                        Range.closedOpen(0, heightMap.length).contains(rowIndex + 1) ?
                                Optional.of(heightMap[rowIndex + 1][columnIndex]) :
                                Optional.empty();

                Optional<Integer> eastHeight =
                        Range.closedOpen(0, heightMap[rowIndex].length).contains(columnIndex - 1) ?
                                Optional.of(heightMap[rowIndex][columnIndex - 1]) :
                                Optional.empty();

                Optional<Integer> westHeight =
                        Range.closedOpen(0, heightMap[rowIndex].length).contains(columnIndex + 1) ?
                                Optional.of(heightMap[rowIndex][columnIndex + 1]) :
                                Optional.empty();

                int currentHeight = heightMap[rowIndex][columnIndex];

                if (northHeight.isPresent() && northHeight.get() <= currentHeight) continue;
                if (southHeight.isPresent() && southHeight.get() <= currentHeight) continue;
                if (eastHeight.isPresent() && eastHeight.get() <= currentHeight) continue;
                if (westHeight.isPresent() && westHeight.get() <= currentHeight) continue;

                lowCoordinates.add(new HeightCoordinate(columnIndex, rowIndex, currentHeight));
            }
        }

        return lowCoordinates;
    }
}
