package day2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import util.AocUtil;
import util.DayInterface;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static day2.Day2.Shape.*;

public class Day2 implements DayInterface {

    private static Map<Shape, Shape> myWin = Map.of(
            ROCK, SCISSORS,
            PAPER, ROCK,
            SCISSORS, PAPER
    );


    public String puzzle1(String path) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(path);

        Map<String, String> plays = new HashMap<>();
        plays.put("A", "Y"); // Rock - Paper
        plays.put("B", "X"); // Paper - Rock
        plays.put("C", "Z"); // Scissors - Scissors

        int totalScore = 0;
        for (String round : lines) {
            Shape elfShape = fromElfPlay(round.split(" ")[0]);
            Shape myShape = fromMyPlay(round.split(" ")[1]);

            int roundScore = roundResultScore(elfShape, myShape);
            roundScore += getPlayScore(myShape);

            totalScore += roundScore;
        }

        return Integer.toString(totalScore);
    }

    public String puzzle2(String path) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(path);

        Map<Shape, Shape> roundWinMap = new HashMap<>();
        roundWinMap.put(ROCK, PAPER); // Rock - Paper
        roundWinMap.put(PAPER, SCISSORS); // Paper - Scissors
        roundWinMap.put(SCISSORS, ROCK); // Scissors - Rock

        Map<Shape, Shape> roundLoseMap = new HashMap<>();
        roundLoseMap.put(ROCK, SCISSORS); // Rock - Scissors
        roundLoseMap.put(PAPER, ROCK); // Paper - Rock
        roundLoseMap.put(SCISSORS, PAPER); // Scissors - Scissors

        // X -> I need to lose
        // Y -> Draw
        // Z -> I need to win

        int totalScore = 0;
        for (String round : lines) {
            Shape elfShape = fromElfPlay(round.split(" ")[0]);
            String roundOutcome = round.split(" ")[1];

            Shape myShape = switch (roundOutcome) {
                case "X" -> roundLoseMap.get(elfShape);
                case "Y" -> elfShape;
                case "Z" -> roundWinMap.get(elfShape);
                default -> throw new IllegalArgumentException();
            };

            int roundScore = roundResultScore(elfShape, myShape);
            roundScore += getPlayScore(myShape);

            totalScore += roundScore;
        }

        return Integer.toString(totalScore);
    }

    private int roundResultScore(Shape elfShape, Shape myShape) {
        // is draw?
        if (elfShape == myShape) {
            return 3;
        }

        // is my win?
        if (elfShape == myWin.get(myShape)) {
            return 6;
        }

        // Elf win
        return 0;
    }

    private int getPlayScore(Shape myShape) {
        return switch (myShape) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
        };
    }

    @Getter
    @AllArgsConstructor
    enum Shape {
        ROCK,
        PAPER,
        SCISSORS;

        public static Shape fromElfPlay(String symbol) {
            return switch (symbol) {
                case "A" -> Shape.ROCK;
                case "B" -> Shape.PAPER;
                case "C" -> Shape.SCISSORS;
                default -> throw new IllegalArgumentException();
            };
        }
        public static Shape fromMyPlay(String symbol) {
            return switch (symbol) {
                case "X" -> Shape.ROCK;
                case "Y" -> Shape.PAPER;
                case "Z" -> Shape.SCISSORS;
                default -> throw new IllegalArgumentException();
            };
        }
    }
}
