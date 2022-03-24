
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CleverSIDC s = new CleverSIDC();
        //readNASTAFiles(s);
        System.out.println("====================================================");
        Demo1(s);
        System.out.println("====================================================");
        Demo2(s);
        System.out.println("====================================================");
        Demo3(s);
        System.out.println("====================================================");
        Demo4(s);
        System.out.println("====================================================");
        Demo5(s);
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
                    sidc.add(id, "");
                }
                System.out.println(c + " file has been completed.");
                System.out.println("Current ADT Size: " + sidc.size());
                sidc.reset();

            }
            scanner.close();
            System.out.println(String.format("Total time to complete (ms): %d", (System.currentTimeMillis()) -start));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open the input file! " + e.getMessage());
        }
    }

    /**
     * Add 10 random keys and delete the last one.
     * @param cleversidc Instance of CleverSIDC class.
     */
    public static void Demo1(CleverSIDC cleversidc) {
        System.out.println("Generating 10 random IDs and adding them to the CleverSIDC");
        int generated = 0;
        for(int i = 0; i < 10; i++) {
            generated = cleversidc.generate();
            cleversidc.add(generated, " ");
        }
        System.out.println("Completed. Current size: " + cleversidc.size());
        System.out.println("Removing the last generated key");
        cleversidc.remove(generated);
        System.out.println("Entry deleted. Current Size: " + cleversidc.size());
        cleversidc.reset();
    }

    /**
     * Demonstrates the transformation of the ADT
     * @param cleversidc Instance of CleverSIDC class.
     */
    public static void Demo2(CleverSIDC cleversidc) {
        int COUNT = 1000;
        System.out.println(String.format("Inserting %d SIDCs and adding them to the CleverSIDC", COUNT));
        for (int i = 0; i < COUNT; i++) {
            cleversidc.add(i, " ");
        }
        System.out.println(String.format("CleverSIDC is currently in %s mode with %d entries.", cleversidc.currentMode(), cleversidc.size()));

        System.out.println("Adding one more key...");
        cleversidc.add(COUNT+1, " ");
        System.out.println(String.format("CleverSIDC is currently in %s mode with %d entries.", cleversidc.currentMode(), cleversidc.size()));

        System.out.println("Removing one key...");
        cleversidc.remove(COUNT+1);
        System.out.println(String.format("CleverSIDC is currently in %s mode with %d entries.", cleversidc.currentMode(), cleversidc.size()));

        cleversidc.reset();
    }

    /**
     *
     * @param cleversidc Instance of CleverSIDC class.
     */
    public static void Demo3(CleverSIDC cleversidc) {
        System.out.println("Inserting 5 SIDCs");
        cleversidc.add(11111111, "John Smith");
        cleversidc.add(22222222, "Joe Bob");
        cleversidc.add(33333333, "John Doe");
        cleversidc.add(44444444, "Barbara Smith");
        cleversidc.add(55555555, "Melinda Gates");

        System.out.println("Range of keys between first and last SIDCs: " + cleversidc.rangeKey(11111111, 55555555));
        System.out.println("Getting values for the last SIDC: " + cleversidc.getValues(55555555));
        System.out.println("Calling remove on the last SIDC: " + cleversidc.remove(55555555));
        System.out.println("Attempting to retrieve the deleted SIDC: " + cleversidc.getValues(55555555));


        cleversidc.reset();
    }

    /**
     *
     * @param cleversidc Instance of CleverSIDC class.
     */
    public static void Demo4(CleverSIDC cleversidc) {
        System.out.println("Inserting 10 random SIDCs");
        for(int i = 0; i < 10; i++) {
            cleversidc.add(cleversidc.generate(), " ");
        }
        System.out.println("Printing all keys (sorted): " + Arrays.toString(cleversidc.allKeys()));
        cleversidc.reset();
    }

    /**
     *
     * @param cleversidc Instance of CleverSIDC class.
     */
    public static void Demo5(CleverSIDC cleversidc) {
        int COUNT = 100000;
        System.out.println(String.format("Inserting %d random SIDCs", COUNT));
        int first = cleversidc.generate();
        cleversidc.add(first, "John Smoth");

        int last = 0;
        for(int i = 0; i < COUNT-1; i++) {
            last = cleversidc.generate();
            cleversidc.add(last, " ");
        }

        System.out.println(String.format("Current value for SIDC %d: %s", first, cleversidc.getValues(first)));
        System.out.println(String.format("Calling add for %d to fix name spelling error: %s", first, cleversidc.add(first, "John Smith")));
        System.out.println(String.format("Done. Current value for SIDC %d after fix: %s", first, cleversidc.getValues(first)));

        cleversidc.reset();
    }
}
