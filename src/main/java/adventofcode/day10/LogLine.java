package adventofcode.day10;

import lombok.Getter;
import lombok.ToString;

import java.util.Stack;

@ToString
class LogLine {
    @Getter private LineStatusEnum lineStatusEnum;
    @Getter private String foundUnexpectedChar;
    @Getter private String data;
    @Getter private Stack<Chunk> chunkStack;
    @Getter private long completionScore;

    public LogLine(String data) {
        this.data = data;
        this.chunkStack = new Stack<>();
        this.completionScore = 0;

        this.processLine();
    }

    private void processLine() {
        this.lineStatusEnum = LineStatusEnum.VALID;

        for (char ch : data.toCharArray()) {
            String stringValue = Character.toString(ch);

            boolean isStartingChar = ChunkUtils.isStartingChar(stringValue);

            if (isStartingChar) {
                Chunk chunk = new Chunk(stringValue);
                this.chunkStack.push(chunk);
            } else {
                Chunk chunk = this.chunkStack.peek();

                if (chunk.endMatchesStart(stringValue)) {
                    this.chunkStack.pop();
                } else {
                    this.lineStatusEnum = LineStatusEnum.CORRUPT;
                    this.foundUnexpectedChar = stringValue;
                    return;
                }
            }
        }

        if (!this.chunkStack.isEmpty()) {
            this.lineStatusEnum = LineStatusEnum.INCOMPLETE;
        }
    }

    public void completeChunk() {
        while (!getChunkStack().isEmpty()) {
            Chunk chunk = getChunkStack().pop();
            String endingChar = ChunkUtils.startingCharToEndingCharMap.get(chunk.getStartingCharacter());

            this.data = this.data.concat(endingChar);

            this.completionScore *= 5;
            this.completionScore += ChunkUtils.endingCharToCompletionScoreMap.get(endingChar);
            System.out.printf("");
        }

        this.processLine();
    }
}
