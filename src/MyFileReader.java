import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {

    public static List<String> randomWordsList() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C://Users//Uporabnik//IdeaProjects//Galgenmaennchen//src//randomWords.txt"));
            List<String> randomWords = new ArrayList<String>();
            String line = br.readLine();

            while (line != null) {
                randomWords.add(line);
                line = br.readLine();
            }

            return randomWords;
        } catch (Exception e) {
            System.out.println("File not found." + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception f) {
                System.out.println("An error occurred.");
            }
        }
        return null;
    }
}
