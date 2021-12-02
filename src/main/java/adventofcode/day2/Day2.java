package adventofcode.day2;

import adventofcode.util.AocUtils;

import java.util.LinkedList;

public class Day2 {
    public int part1() {
        LinkedList<String> commands = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day2/movementinput.txt");

        int horizontalPos = 0;
        int depthPos = 0;

        for (String command : commands) {
            String[] commandSplit = command.split(" ");

            int horizontalDelta = 0;
            int depthDelta = 0;

            switch (commandSplit[0].toLowerCase()) {
                case "forward":
                    horizontalDelta = Integer.parseInt(commandSplit[1]);
                    break;
                case "up":
                    depthDelta = Integer.parseInt(commandSplit[1]) * -1;
                    break;
                case "down":
                    depthDelta = Integer.parseInt(commandSplit[1]);
                    break;
            }

            horizontalPos += horizontalDelta;
            depthPos += depthDelta;
        }

        return horizontalPos * depthPos;
    }

    public int part2() {
        LinkedList<String> commands = (LinkedList<String>) AocUtils.getLinesFromFile("src/main/resources/day2/movementinput.txt");

        int horizontalPos = 0;
        int depthPos = 0;
        int aim = 0;

        for (String commandLine : commands) {
            String[] commandSplit = commandLine.split(" ");

            String command = commandSplit[0];
            int commandValue = Integer.parseInt(commandSplit[1]);

            int horizontalDelta = 0;
            int depthDelta = 0;
            int aimDelta = 0;

            switch (command.toLowerCase()) {
                case "forward":
                    horizontalDelta = commandValue;
                    depthDelta = commandValue * aim;
                    break;
                case "up":
                    aimDelta = commandValue * -1;
                    break;
                case "down":
                    aimDelta = commandValue;
                    break;
            }

            horizontalPos += horizontalDelta;
            depthPos += depthDelta;
            aim += aimDelta;
        }

        return horizontalPos * depthPos;
    }
}
