package adventofcode.day4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BingoBoard {
    // Rows of columns
    private ArrayList<ArrayList<BingoCell>> board;

    @Getter private int lastDrawnNumber;
    @Getter private int drawnCount;
    @Getter private int finalScore;
    @Getter boolean isProcessed;

    public BingoBoard(List<String> rowList) {
        this.board = new ArrayList<>();

        rowList.forEach(row -> {
            ArrayList<BingoCell> columns = new ArrayList<>();
            String[] rowSplit = row.split(" ");

            for (String cell : rowSplit) {
                if (cell.equals("")) continue;

                columns.add(new BingoCell(Integer.parseInt(cell), false));
            }

            board.add(columns);
        });
    }

    public void mark(int value) {
        board.forEach(row -> row.forEach(cell -> {
            if (cell.getValue() == value) {
                cell.setMarked(true);
            }
        }));
    }

    public boolean isWinning() {
        // Check rows
        boolean hasWinningRows = board
                .stream()
                .anyMatch(row -> row.stream().allMatch(BingoCell::isMarked));

        // Return if we have winning rows, no need to process further
        if (hasWinningRows)
            return true;

        // First row has no marked cells then it's impossible to have a fully marked column
        boolean firstRowHasMarkedCells = board.get(0).stream().anyMatch(BingoCell::isMarked);
        if (!firstRowHasMarkedCells){
            return false;
        }

        boolean hasWinningColumns = false;
        for (int columnIndex = 0; columnIndex < board.get(0).size(); columnIndex++) {
            final int finalColumnIndex = columnIndex;

            if (board.stream().allMatch(row -> row.get(finalColumnIndex).isMarked())) {
                hasWinningColumns = true;
                break;
            }
        }

        return hasWinningColumns;
    }

    public int getSumOfUnmarkedCells() {
        return board.stream()
                .flatMap(Collection::stream)
                .filter(bingoCell -> !bingoCell.isMarked())
                .mapToInt(BingoCell::getValue)
                .sum();
    }

    public void processBoard(List<Integer> drawingSequence) {
        for (Integer drawnNumber : drawingSequence) {
            this.mark(drawnNumber);
            this.drawnCount++;
            this.lastDrawnNumber = drawnNumber;

            if (isWinning()) {
                this.finalScore = this.getSumOfUnmarkedCells() * this.lastDrawnNumber;
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        // Iterate rows
        for (ArrayList<BingoCell> row : board) {
            for (BingoCell cell : row) {

                String format = " %02d ";
                if (cell.isMarked()) {
                    format = "{%02d}";
                }

                stringBuilder.append(String.format(format, cell.getValue()));
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
