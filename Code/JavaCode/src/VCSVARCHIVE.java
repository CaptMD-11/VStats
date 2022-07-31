import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class VCSVARCHIVE {
    // 0-based index
    public static Object[] getColumn(String fileName, int col) {

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
                    ArrayList<String> tempStrArr = getColumnOfStrings(fileName, col);
                    String[] strOutput = new String[tempStrArr.size()];
                    for (int j = 0; j < strOutput.length; j++) {
                        strOutput[j] = tempStrArr.get(j);
                    }
                    return strOutput;
                }
            }
            ArrayList<Double> tempDoubleArr1 = getColumnOfNumbers(fileName, col);
            Double[] numberOutput1 = new Double[tempDoubleArr1.size()];
            for (int i = 0; i < numberOutput1.length; i++) {
                numberOutput1[i] = tempDoubleArr1.get(i);
            }
            return numberOutput1;

        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Double> tempDoubleArr2 = getColumnOfNumbers(fileName, col);
        Double[] numberOutput2 = new Double[tempDoubleArr2.size()];
        for (int i = 0; i < numberOutput2.length; i++) {
            numberOutput2[i] = tempDoubleArr2.get(i);
        }
        return numberOutput2;

    }
}