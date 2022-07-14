import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    private File file;

    public CSVParser() {

    }

    public ArrayList<String> changeLastToNull() {

        ArrayList<String> res = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();

            while (scanner.hasNextLine()) {
                if (line.substring(line.length() - 1).equals(","))
                    line += "null";
                res.add(line);
                line = scanner.nextLine();
            }

            return res;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

}