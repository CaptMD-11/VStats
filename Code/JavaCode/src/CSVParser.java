import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {

    private CSVParser() {

    }

    // HELPER METHOD
    private static int getNthIndexOf(String line, int n, String str) {
        int count = 0;
        ArrayList<Integer[]> tracker = new ArrayList<Integer[]>();

        for (int i = 0; i < line.length(); i++) {
            if (line.substring(i, i + 1).equals(str)) {
                count++;
                Integer[] arr = { count, i };
                tracker.add(arr);
            }
        }

        for (int i = 0; i < tracker.size(); i++) {
            if (n == tracker.get(i)[0])
                return tracker.get(i)[1];
        }

        return 0;
    }

    public static int getNumCols(String fileName) {

        File file = new File(fileName);

        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.substring(i, i + 1).equals(","))
                    count++;
            }
            return count + 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // 0-based index logic
    private static ArrayList<String> getColumnOfStrings(String fileName, int col) {

        File file = new File(fileName);
        ArrayList<String> res = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();

            while (line.length() != 0) {
                if (col == 0)
                    res.add((line.substring(0, line.indexOf(","))));
                else if (col == getNumCols(fileName) - 1)
                    res.add(line.substring(line.lastIndexOf(",") + 1));
                else
                    res.add(line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line,
                            col + 1, ",")));
                line = scanner.nextLine();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return res;

    }

    // 0-based index logic
    private static ArrayList<Double> getColumnOfNumbers(String fileName, int col) {

        File file = new File(fileName);
        ArrayList<Double> res = new ArrayList<Double>();

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();

            while (line.length() != 0) {
                if (col == 0)
                    res.add(Double.parseDouble(line.substring(0, line.indexOf(","))));
                else if (col == getNumCols(fileName) - 1)
                    res.add(Double.parseDouble(line.substring(line.lastIndexOf(",") + 1)));
                else
                    res.add(Double.parseDouble(
                            line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line, col +
                                    1, ","))));
                line = scanner.nextLine();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return res;

    }

    // 0-based index
    public static ArrayList getColumn(String fileName, int col) {

        File file = new File(fileName);
        ArrayList res;

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();

            String temp = "";
            String numChars = "+-.1234567890";

            if (col == 0)
                temp = line.substring(0, line.indexOf(","));
            else if (col == getNumCols(fileName) - 1)
                temp = line.substring(line.lastIndexOf(",") + 1);
            else
                temp = line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line,
                        col + 1, ","));

            for (int i = 0; i < temp.length(); i++) {
                if (numChars.indexOf(temp.substring(i, i + 1)) == -1) {
                    res = new ArrayList<String>();
                    return getColumnOfStrings(fileName, col);
                }
            }

            return getColumnOfNumbers(fileName, col);

        } catch (Exception e) {
            System.out.println(e);
        }

        return getColumnOfNumbers(fileName, col);

    }

}