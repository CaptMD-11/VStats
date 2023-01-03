import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class VCSV {

    private VCSV() {

    }

    // GOOD
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
    public static double[] getColumnOfNumbers(String fileName, int col) {
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
        double[] output = new double[res.size()];
        for (int i = 0; i < res.size(); i++) {
            output[i] = res.get(i);
        }
        return output;
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

}