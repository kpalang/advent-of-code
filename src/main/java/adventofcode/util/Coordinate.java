package adventofcode.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
public class Coordinate {
    @Getter private int posX;
    @Getter private int posY;

    @Override
    public String toString() {
        return String.format("[X: %d, Y: %d]", posX, posY);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate coordinate = (Coordinate) obj;

        return this.posX == coordinate.getPosX() && this.posY == coordinate.getPosY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
