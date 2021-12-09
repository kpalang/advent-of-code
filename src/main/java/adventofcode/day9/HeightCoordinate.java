package adventofcode.day9;

import adventofcode.util.Coordinate;
import lombok.Getter;

public class HeightCoordinate extends Coordinate {
    @Getter private int height;

    public HeightCoordinate(int posX, int posY, int height) {
        super(posX, posY);
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("[X: %d, Y: %d, Height: %d]", getPosX(), getPosY(), getHeight());
    }
}
