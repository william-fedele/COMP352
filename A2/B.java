import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class B {

    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            int lineCount = 0;
            Scanner scanner = new Scanner(new File(inputFile));
            if(scanner.hasNextLine())
                lineCount = Integer.parseInt(scanner.nextLine());

            while(scanner.hasNextLine() && lineCount > 0) {
                String[] lineString = scanner.nextLine().split(" ");
                //int n = Integer.parseInt(lineString[0]);
                int startIndex = Integer.parseInt(lineString[1]);

                // Convert the array from string to ints
                int[] lineInt = new int[lineString.length-1];
                for (int i = 2; i < lineString.length; i++)
                    lineInt[i-2] = Integer.parseInt(lineString[i]);
                lineInt[lineInt.length-1] = 0;

                // Stores the next move to try on top
                Stack<Integer> stack = new Stack<>();

                int valid = HitZero(lineInt, startIndex, stack);

                // Write result to output file
                Files.write(Paths.get(outputFile),
                        (valid + (scanner.hasNextLine() ? "\n": ""))
                                .getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);

                lineCount--;
            }
            scanner.close();
        }catch (FileNotFoundException e) {
            System.out.println("Failed to open the input file! " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int HitZero(int[] A, int startIndex, Stack<Integer> S) {
        int currentIndex;
        boolean found = false;
        int[] visits = new int[A.length];
        S.push(startIndex);

        while(!S.empty()) {

            currentIndex = S.pop();
            visits[currentIndex] = 1;

            // check for win condition
            if(A[currentIndex] == 0) {
                found = true;
                break;
            }
            if(A[currentIndex] > A.length)
                continue;

            // check if moving left is valid
            // must be within array boundaries and must not return to the previous position
            if(currentIndex - A[currentIndex] >= 0 && visits[currentIndex - A[currentIndex]] != 1)
                S.push(currentIndex - A[currentIndex]);

            // check if moving right is valid
            // must be within array boundaries and must not return to the previous position
            if(currentIndex + A[currentIndex] < A.length && visits[currentIndex + A[currentIndex]] != 1)
                S.push(currentIndex + A[currentIndex]);
        }

        // either all routes exhausted or hit zero
        return found ? 1 : 0;

    }

}
