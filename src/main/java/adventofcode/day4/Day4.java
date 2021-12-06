package adventofcode.day4;

import adventofcode.util.AocUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    public int part1() {
        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day4/bingoinput.txt");
        //List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day4/bingoinput.txt");

        List<BingoBoard> bingoBoards = getListOfBoards(lines);
        List<Integer> drawingSequence = getDrawingSequence(lines);

        bingoBoards.forEach(bingoBoard -> bingoBoard.processBoard(drawingSequence));
        BingoBoard winningBoard = Collections.min(bingoBoards, Comparator.comparingInt(BingoBoard::getDrawnCount));

        return winningBoard.getFinalScore();
    }

    public int part2() {
        List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day4/bingoinput.txt");
        //List<String> lines = AocUtils.getLinesFromFile("src/main/resources/day4/tonysinput.txt");

        List<BingoBoard> bingoBoards = getListOfBoards(lines);
        List<Integer> drawingSequence = getDrawingSequence(lines);

        bingoBoards.forEach(bingoBoard -> bingoBoard.processBoard(drawingSequence));
        BingoBoard losingBoard = Collections.max(bingoBoards, Comparator.comparingInt(BingoBoard::getDrawnCount));

        return losingBoard.getFinalScore();
    }

    private List<Integer> getDrawingSequence(List<String> lines) {
        return Stream
                .of(lines.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<BingoBoard> getListOfBoards(List<String> lines) {
        List<BingoBoard> bingoBoards = new ArrayList<>();

        List<String> boardRowsCollector = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {

            String line = lines.get(i).trim();

            if (line.length() == 0) {
                if (boardRowsCollector.size() == 0) continue;

                // Create a board with collected rows and add it to list
                bingoBoards.add(new BingoBoard(boardRowsCollector));

                // Reset row collector
                boardRowsCollector = new ArrayList<>();
            } else {
                boardRowsCollector.add(line);
            }
        }

        return bingoBoards;
    }
}
