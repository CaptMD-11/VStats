import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    private File file;

    public CSVParser() {
        file = new File("menu.csv");
    }

    // HELPER METHOD
    public int getNumCols() {

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

    // HELPER METHOD
    private int getNthIndexOf(String line, int n, String str) {
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

    // 0-based index logic
    public ArrayList<String> getColumnOfStrings(int col) {

        ArrayList<String> res = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();

            while (line.length() != 0) {
                if (col == 0)
                    res.add((line.substring(0, line.indexOf(","))));
                else if (col == getNumCols() - 1)
                    res.add(line.substring(line.lastIndexOf(",") + 1));
                else
                    res.add(line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line, col + 1, ",")));
                line = scanner.nextLine();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return res;

    }

    // 0-based index logic
    public ArrayList<Double> getColumnOfNumbers(int col) {

        ArrayList<Double> res = new ArrayList<Double>();

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            line = scanner.nextLine();

            while (line.length() != 0) {
                if (col == 0)
                    res.add(Double.parseDouble(line.substring(0, line.indexOf(","))));
                else if (col == getNumCols() - 1)
                    res.add(Double.parseDouble(line.substring(line.lastIndexOf(",") + 1)));
                else
                    res.add(Double.parseDouble(
                            line.substring(getNthIndexOf(line, col, ",") + 1, getNthIndexOf(line, col + 1, ","))));
                line = scanner.nextLine();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return res;

    }

}