import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            int lineCount = 0;
            Scanner scanner = new Scanner(new File(inputFile));
            if(scanner.hasNextLine())
                lineCount = Integer.parseInt(scanner.nextLine());

            while(scanner.hasNextLine() && lineCount > 0) {
                // Get line and parse out metadata
                String[] lineString = scanner.nextLine().split(" ");
                //int n = Integer.parseInt(lineString[0]);
                int startIndex = Integer.parseInt(lineString[1]);

                // Convert the array from string to ints
                Integer[] lineInt = new Integer[lineString.length-1];
                for (int i = 2; i < lineString.length; i++)
                    lineInt[i-2] = Integer.parseInt(lineString[i]);
                lineInt[lineInt.length-1] = 0;

                // Tracks index visits
                int[] visits = new int[lineInt.length];
                // Tracks game completion
                int done = 0;
                int valid = HitZero(lineInt, startIndex, visits, done);

                // Write result to output file
                Files.write(Paths.get(outputFile),
                        (valid + (scanner.hasNextLine() ? "\n": ""))
                                .getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);

                lineCount--;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open the input file! " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int HitZero(Integer[] A, int marker, int[] visits, int done) {
        // Boundary check
        if(marker < 0 || marker >= A.length) return 0;
        if(A[marker] > A.length) return 0;

        // Endpoint check
        if(A[marker] == 0) {
            return 1;
        };

        // Mark the array index 'seen'
        visits[marker] = 1;
        if(done == 0 && marker - A[marker] >= 0 && visits[marker - A[marker]] != 1)
            done = HitZero(A, marker - A[marker], visits, done);
        if(done == 0 && marker + A[marker] < A.length && visits[marker + A[marker]] != 1)
            done = HitZero(A, marker + A[marker], visits, done);

        return done;
    }

}
