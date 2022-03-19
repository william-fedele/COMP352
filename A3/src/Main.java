import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            String[] inputFiles = new String[] {"NASTA_test_file1.txt", "NASTA_test_file2.txt", "NASTA_test_file3.txt"};
            Scanner scanner = new Scanner(new File(inputFiles[0]));
            CleverSIDC sidc = new CleverSIDC();
            while(scanner.hasNextLine()) {
                int id = Integer.parseInt(scanner.nextLine());
                sidc.add(id, "random value");
            }
            System.out.println(sidc.getValues(86148178));
            sidc.remove(86148178);
            System.out.println(sidc.getValues(86148178));

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open the input file! " + e.getMessage());
        }
    }
}
