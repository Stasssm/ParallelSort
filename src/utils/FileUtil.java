package utils;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

import static utils.Constants.DISTRIBUTION;
import static utils.Constants.ELEMENTS_NUMBER;

/**
 * Created by Стас on 7/1/2017.
 */
public class FileUtil {

    public static int[] fromFile(String fileName) {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            int[] elements = new int[ELEMENTS_NUMBER];
            int i = 0;
            while (scanner.hasNextInt()) {
                elements[i++] = scanner.nextInt();
            }
            return elements;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void toFile(String fileName, int[] data) {
        // The target file
        File out = new File(fileName);
        FileWriter fw = null;
        int n = ELEMENTS_NUMBER;
        try {
            // Create file writer object
            fw = new FileWriter(out);
            // Wrap the writer with buffered streams
            BufferedWriter writer = new BufferedWriter(fw);
            for (int aData : data) {
                writer.write(String.valueOf(aData));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }


    public static void generateRandomData(String fileName) {
        int[] data = new int[ELEMENTS_NUMBER];
        Random random = new Random();
        IntStream.range(0, data.length)
                .forEach(i -> data[i] = random.nextInt(DISTRIBUTION));
        toFile(fileName, data);
    }


}
