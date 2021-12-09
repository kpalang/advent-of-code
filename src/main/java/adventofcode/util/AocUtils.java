package adventofcode.util;

import adventofcode.util.exception.CoordinateNotOnMapException;
import com.google.common.collect.Range;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AocUtils {
    public static List<String> getLinesFromFile(String path) {
        LinkedList<String> result = new LinkedList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream(path);
                Scanner sc = new Scanner(fileInputStream)
        ) {
            while (sc.hasNextLine()) result.add(sc.nextLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String print2dArray(Object[][] array2d) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int rowIndex = 0; rowIndex < array2d.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < array2d[rowIndex].length; columnIndex++) {
                Object value = array2d[rowIndex][columnIndex];
                stringBuilder.append(value.toString()).append(" | ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public static boolean isCoordinateOn2dArray(Integer[][] map, Coordinate coordinate) {

        // Check if map contains center coordinate Y
        if (!Range.closedOpen(0, map.length).contains(coordinate.getPosY())) {
            return false; //throw new CoordinateNotOnMapException(String.format("Map does not contain coordinate %s", centerCoordinate));
        }

        // Check if map contains center coordinate X
        if (!Range.closedOpen(0, map[coordinate.getPosY()].length).contains(coordinate.getPosX())) {
            return false; //throw new CoordinateNotOnMapException(String.format("Map does not contain coordinate %s", centerCoordinate));
        }

        return true;
    }
}
