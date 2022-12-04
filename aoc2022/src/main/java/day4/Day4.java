package day4;

import lombok.Getter;
import util.AocUtil;
import util.DayInterface;

import java.util.LinkedList;
import java.util.List;

public class Day4 implements DayInterface {
    @Override
    public String puzzle1(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        List<ElfPair> elfPairs = lines.stream()
                .map(ElfPair::new)
                .filter(elfPair -> {
                    ElfPair.ElfSection firstSection = elfPair.firstSection;
                    ElfPair.ElfSection secondSection = elfPair.secondSection;

                    return firstSection.contains(secondSection) || secondSection.contains(firstSection);
                })
                .toList();

        return String.valueOf(elfPairs.size());
    }

    @Override
    public String puzzle2(String inputPath) {
        LinkedList<String> lines = (LinkedList<String>) AocUtil.getLinesFromFile(inputPath);

        long overlappingPairCount = lines.stream()
                .map(ElfPair::new)
                .filter(elfPair -> {
                    ElfPair.ElfSection firstSection = elfPair.firstSection;
                    ElfPair.ElfSection secondSection = elfPair.secondSection;

                    return firstSection.overlaps(secondSection) || secondSection.overlaps(firstSection);
                })
                .count();

        return String.valueOf(overlappingPairCount);
    }

    private class ElfPair {
        private ElfSection firstSection;
        private ElfSection secondSection;

        ElfPair(String pairData) {
            String firstElf = pairData.split(",")[0];
            String secondElf = pairData.split(",")[1];

            firstSection = new ElfSection(firstElf);
            secondSection = new ElfSection(secondElf);
        }

        class ElfSection {
            private int lowerBound;
            private int upperBound;

            ElfSection(String sectionData) {
                lowerBound = Integer.parseInt(sectionData.split("-")[0]);
                upperBound = Integer.parseInt(sectionData.split("-")[1]);
            }

            boolean contains(ElfSection otherSection) {
                return this.lowerBound <= otherSection.lowerBound && this.upperBound >= otherSection.upperBound;
            }

            boolean overlaps(ElfSection otherSection) {
                return
                    (this.lowerBound <= otherSection.lowerBound && otherSection.lowerBound <= this.upperBound)
                    ||
                    (this.lowerBound <= otherSection.upperBound && otherSection.upperBound <= this.upperBound);

            }
        }
    }
}
