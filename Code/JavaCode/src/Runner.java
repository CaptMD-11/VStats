public class Runner {
    public static void main(String[] args) {

        // System.out.println(VCSV.getNumCols("menu.csv"));

        double[] res = VCSV.getColumnOfNumbers("menu.csv", 0);

        for (double d : res)
            System.out.println(d);

    }
}