import java.io.File;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        File file = new File("collegeadmissions.csv");

        CSVParser obj = new CSVParser();
        ArrayList<String> res = obj.changeLastToNull();

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

    }
}