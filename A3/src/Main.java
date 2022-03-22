
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CleverSIDC s = new CleverSIDC();
        readNASTAFiles(s);


    }

    public static void readNASTAFiles(CleverSIDC sidc) {
        try {
            long start = System.currentTimeMillis();
            String[] inputFiles = new String[] {"NASTA_test_file1.txt", "NASTA_test_file2.txt", "NASTA_test_file3.txt"};
            Scanner scanner = null;

            for (String c : inputFiles) {
                scanner = new Scanner(new File(c));
                while(scanner.hasNextLine()) {
                    int id = Integer.parseInt(scanner.nextLine());
                    sidc.add(id, "random value");
                }
                System.out.println(c + " file has been completed.");
                System.out.println("Current ADT Size: " + sidc.size());
                sidc.reset();

            }
            scanner.close();
            System.out.println(String.format("Time to complete (ms): %d", (System.currentTimeMillis()) -start));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open the input file! " + e.getMessage());
        }
    }

    public static void Demo1(CleverSIDC sidc) {

    }
    public static void Demo2(CleverSIDC sidc) {

    }
    public static void Demo3(CleverSIDC sidc) {

    }
    public static void Demo4(CleverSIDC sidc) {

    }
    public static void Demo5(CleverSIDC sidc) {

    }
}
