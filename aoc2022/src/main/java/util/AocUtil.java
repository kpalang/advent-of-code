package util;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AocUtil {
    public static List<String> getLinesFromFile(String path) {
        LinkedList<String> result = new LinkedList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream(path);
                Scanner sc = new Scanner(fileInputStream)
        ) {
            while (sc.hasNextLine()) result.add(sc.nextLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
