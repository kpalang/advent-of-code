package adventofcode.day4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
class BingoCell {
    @Getter private int value;
    @Getter @Setter private boolean isMarked;
}
