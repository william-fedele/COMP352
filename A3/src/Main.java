import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            String[] inputFiles = new String[] {"NASTA_test_file1.txt", "NASTA_test_file2.txt", "NASTA_test_file3.txt"};
            Scanner scanner = null;
            CleverSIDC sidc = new CleverSIDC();

            for (String c : inputFiles) {
                scanner = new Scanner(new File(c));
                while(scanner.hasNextLine()) {
                    int id = Integer.parseInt(scanner.nextLine());
                    sidc.add(id, "random value");
                }
                System.out.println(sidc.size());
                sidc.empty();
                System.out.println(sidc.size());

            }
            scanner.close();
            System.out.println(String.format("Time to complete (ms): %d", (System.currentTimeMillis()) -start));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open the input file! " + e.getMessage());
        }
    }
}
