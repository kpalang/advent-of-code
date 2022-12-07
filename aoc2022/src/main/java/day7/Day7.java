package day7;

import util.AocUtil;
import util.DayInterface;

import java.util.*;

public class Day7 implements DayInterface {

    @Override
    public String puzzle1(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        AocDir rootDir = generateFileSystem(lines);
        List<AocDir> dirList = new ArrayList<>();
        populateDirSizes(rootDir, dirList);

        int totalSizes = dirList.stream().filter(aocDir -> aocDir.totalDirSize <= 100000).mapToInt(value -> value.totalDirSize).sum();
        return String.valueOf(totalSizes);
    }

    @Override
    public String puzzle2(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        AocDir rootDir = generateFileSystem(lines);
        List<AocDir> dirList = new ArrayList<>();
        populateDirSizes(rootDir, dirList);

        int totalSpace = 70000000;
        int totalUsedSpace = rootDir.totalDirSize;
        int totalUnusedSpace = totalSpace - totalUsedSpace;
        int requiredUnusedSpace = 30000000;
        int requiredSpaceToFree = requiredUnusedSpace - totalUnusedSpace;

        AocDir smallestDeletableDir = dirList.stream()
                .filter(aocDir -> aocDir.totalDirSize >= requiredSpaceToFree)
                .min(Comparator.comparing(aocDir -> aocDir.totalDirSize))
                .orElseThrow(() -> new RuntimeException("Big bad"));

        return String.valueOf(smallestDeletableDir.totalDirSize);
    }

    private int populateDirSizes(AocDir inputDir, List<AocDir> dirAggregator) {
        dirAggregator.add(inputDir);

        int dirsSizes = 0;
        for (AocDir aocDir : inputDir.subDirs) {
            int dirSize = populateDirSizes(aocDir, dirAggregator);
            dirsSizes += dirSize;
        }

        int totalDirSize = inputDir.totalFilesSize + dirsSizes;
        inputDir.totalDirSize = totalDirSize;

        return totalDirSize;
    }

    private AocDir generateFileSystem(LinkedList<String> lines) {
        AocDir rootDir = new AocDir("/", null);
        AocDir currentDir = rootDir;

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).replace("$ ", "");

            if (line.startsWith("cd")) {
                String targetDir = line.split(" ")[1];

                if (targetDir.equals("/")) {
                    currentDir = rootDir;
                } else if (targetDir.equals("..")) {
                    currentDir = currentDir.parent;
                } else {

                    Optional<AocDir> oSubDir = currentDir.getSubDir(targetDir);

                    if (oSubDir.isPresent()) {
                        currentDir = oSubDir.get();
                    }
                }
            } else if (line.startsWith("ls")) {
                // basically do nothing
            } else {

                if (line.startsWith("dir")) {
                    String targetDir = line.split(" ")[1];

                    Optional<AocDir> oSubDir = currentDir.getSubDir(targetDir);

                    if (oSubDir.isEmpty()) {
                        AocDir newSubDir = new AocDir(targetDir, currentDir);
                        currentDir.subDirs.add(newSubDir);
                    }
                } else {
                    String fileSize = line.split(" ")[0];
                    String fileName = line.split(" ")[1];

                    AocFile newFile = new AocFile(fileSize, fileName);

                    if (!currentDir.files.contains(newFile)) {
                        currentDir.addFile(newFile);
                    }
                }
            }
        }

        return rootDir;
    }

    private class AocDir {
        private final List<AocDir> subDirs;
        private final List<AocFile> files;
        private final AocDir parent;

        private int totalFilesSize;
        private int totalDirSize;

        private final String name;

        AocDir(String name, AocDir parent) {
            this.name = name;
            this.parent = parent;

            this.subDirs = new ArrayList<>();
            this.files = new ArrayList<>();

            this.totalFilesSize = 0;
            this.totalDirSize = 0;
        }

        void addFile(AocFile file) {
            this.files.add(file);
            this.totalFilesSize += file.size;
        }

        Optional<AocDir> getSubDir(String targetDir) {
            return this.subDirs.stream().filter(aocDir -> aocDir.name.equals(targetDir)).findFirst();
        }
    }

    private static class AocFile {
        private final int size;
        private final String name;

        AocFile(String size, String name) {
            this.size = Integer.parseInt(size);
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof AocFile aocFile) {
                return this.size == aocFile.size && this.name.equals(aocFile.name);
            }

            return false;
        }
    }
}
