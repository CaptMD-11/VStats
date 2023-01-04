
public class Runner {
    public static void main(String[] args) {

        double[] res = VCSV.getRowOfNumbers("CSVTestFiles/test.csv", 1);

        for (double d : res)
            System.out.println(d);

        // System.out.println(VCSV.getNumCols("menu.csv"));

        // System.out.println(VCSV.getNumRows("menu.csv"));

    }
}