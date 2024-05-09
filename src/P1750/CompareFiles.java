package P1750;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CompareFiles {
    public static void main(String[] args) {
        String file1 = "D:\\Code\\LevOJ\\src\\P1750\\output.txt";
        String file2 = "D:\\Code\\LevOJ\\src\\P1750\\output1.txt";
        System.out.println("start");
        try {
            compareAndPrintDifferences(file1, file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void compareAndPrintDifferences(String file1, String file2) throws FileNotFoundException {
        Scanner scanner1 = new Scanner(new File(file1));
        Scanner scanner2 = new Scanner(new File(file2));

        int lineNumber = 1;
        boolean differencesFound = false;

        while (scanner1.hasNextLine() && scanner2.hasNextLine()) {
            String line1 = scanner1.nextLine();
            String line2 = scanner2.nextLine();

            if (!line1.equals(line2)) {
                System.out.println("Difference found at line " + lineNumber + ":");
                System.out.println(file1 + ": " + line1);
                System.out.println(file2 + ": " + line2);
                differencesFound = true;
            }

            lineNumber++;
        }

        // Check if one file has more lines than the other
        while (scanner1.hasNextLine()) {
            System.out.println("Difference found at line " + lineNumber + ":");
            System.out.println(file1 + ": " + scanner1.nextLine());
            differencesFound = true;
            lineNumber++;
        }

        while (scanner2.hasNextLine()) {
            System.out.println("Difference found at line " + lineNumber + ":");
            System.out.println(file2 + ": " + scanner2.nextLine());
            differencesFound = true;
            lineNumber++;
        }

        scanner1.close();
        scanner2.close();

        if (!differencesFound) {
            System.out.println("Files are identical.");
        }
    }
}

