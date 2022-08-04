import java.io.File;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        // System.out.println(CSVParser.getNumCols("music_genre.csv"));

        // System.out.println(VCSV.getColumn("menu.csv", 16));

        // System.out.println(VStats.computeMean(VCSV.getColumn("menu.csv", 16)));

        // System.out.println(VStats.computeMean(VCSV.getColumn("menu.csv", 16)));

        // System.out.println(VStats.computeMean(VCSV.getColumnOfNumbers("menu.csv",
        // 16)));
        double[] ind = { 2, 4, 2 };
        double[] dep = { 5, 2, 2 };
        System.out.println("asdfasd " + VStats.computeSe(ind, dep));
        System.out.println(VStats.computeSumOfResidualsSquared(ind, dep));
    }
}