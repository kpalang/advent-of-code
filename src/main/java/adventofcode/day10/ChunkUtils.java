package adventofcode.day10;

import java.util.Map;

class ChunkUtils {
    public static Map<String, String> startingCharToEndingCharMap = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "<", ">");

    public static Map<String, String> endingCharToStartingCharMap = Map.of(
            ")", "(",
            "]", "[",
            "}", "{",
            ">", "<");

    public static Map<String, Integer> endingCharToScoreMap = Map.of(
            ")", 3,
            "]", 57,
            "}", 1197,
            ">", 25137);

    public static Map<String, Integer> endingCharToCompletionScoreMap = Map.of(
            ")", 1,
            "]", 2,
            "}", 3,
            ">", 4);

    public static boolean isStartingChar(String character) {
        return startingCharToEndingCharMap.containsKey(character);
    }
}
