package adventofcode.day10;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Chunk {
    @Getter private String startingCharacter;

    public Chunk(String startingCharacter) {
        this.startingCharacter = startingCharacter;
    }

    public boolean endMatchesStart(String endingChar) {
        return ChunkUtils.endingCharToStartingCharMap.get(endingChar).equals(startingCharacter);
    }
}
