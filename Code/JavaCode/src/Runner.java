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

        // System.out.println(VStats.computeZStar(0.95));

        // System.out.println(VStats.computeTwoPropZTestP1GreaterThanP2(10, 100, 9, 100,
        // 0.05));

        // System.out.println(VStats.computeInverseNormalApprox(0.5));

        System.out.println(VStats.computeTwoPropZTestP1NotEqualToP2(10, 100, 20, 100, 0.05));

    }
}