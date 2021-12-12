package adventofcode.day11;

import adventofcode.util.Coordinate;
import lombok.Getter;

public class Octopus extends Coordinate {
    @Getter private int energy;

    public Octopus(int posX, int posY, int energy) {
        super(posX, posY);
        this.energy = energy;
    }

    @Override
    public String toString() {
        return String.format("[X: %d, Y: %d, Height: %d]", getPosX(), getPosY(), getEnergy());
    }
}
