import java.io.File;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        // File file = new File("menu.csv");

        CSVParser obj = new CSVParser();

        // System.out.println(obj.getNumRows());

        // System.out.println(obj.getNumCols());

        System.out.println(obj.getColumn(23));

    }
}