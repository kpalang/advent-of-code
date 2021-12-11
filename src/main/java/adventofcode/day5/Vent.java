package adventofcode.day5;

import adventofcode.util.Coordinate;
import adventofcode.util.Direction;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
public class Vent {

    @Getter private Coordinate startCoordinate;
    @Getter private Coordinate endCoordinate;
    @Getter private Direction direction;

    private static final Pattern pattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

    public Vent(String inputLine) {
        Matcher matcher = pattern.matcher(inputLine);

        if (matcher.matches()) {
            startCoordinate = new Coordinate(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            endCoordinate = new Coordinate(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        } else {
            throw new RuntimeException("Line does not match input regex");
        }

        this.setDirection();
        this.normalize();
    }

    private void setDirection() {
        if (startCoordinate.getPosY() == endCoordinate.getPosY()) {
            this.direction = Direction.HORIZONTAL;
        } else if (startCoordinate.getPosX() == endCoordinate.getPosX()) {
            this.direction = Direction.VERTICAL;
        } else {
            if (startCoordinate.getPosY() < endCoordinate.getPosY()) {
                this.direction = Direction.DIAGONAL_RISING;
            } else {
                this.direction = Direction.DIAGONAL_FALLING;
            }
        }
    }

    private void normalize() {
        Coordinate tempCoordinate = startCoordinate;

        if (this.direction == Direction.HORIZONTAL
                || this.direction == Direction.DIAGONAL_RISING
                || this.direction == Direction.DIAGONAL_FALLING) {
            int minX = Math.min(startCoordinate.getPosX(), endCoordinate.getPosX());
            int maxX = Math.max(startCoordinate.getPosX(), endCoordinate.getPosX());
            startCoordinate = new Coordinate(minX, tempCoordinate.getPosY());
            endCoordinate = new Coordinate(maxX, tempCoordinate.getPosY());
        } else if (this.direction == Direction.VERTICAL) {
            int minY = Math.min(startCoordinate.getPosY(), endCoordinate.getPosY());
            int maxY = Math.max(startCoordinate.getPosY(), endCoordinate.getPosY());
            startCoordinate = new Coordinate(tempCoordinate.getPosX(), minY);
            endCoordinate = new Coordinate(tempCoordinate.getPosX(), maxY);
        } else {
            throw new RuntimeException("Could not normalize");
        }
    }
}
