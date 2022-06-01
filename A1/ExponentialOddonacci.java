
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Oddonacci Algorithm with Exponential Complexity
 */
public class ExponentialOddonacci {

    static final String FILENAME = "out.txt";
    static final int NTH_ODDONACCI = 100;

    public static void main(String[] args) {

        long startTime, duration;
        try {

            for (int i = 5; i <= NTH_ODDONACCI; i += 5){
                System.out.println(String.format("Testing exponential Oddonacci with n=%d",i));

                // Record execution time for the ith Oddonacci
                startTime=System.nanoTime();
                BigInteger iOddonacci = Oddonacci(i);
                duration=(System.nanoTime()-startTime);

                Files.write(Paths.get(FILENAME),
                        String.format("Exponential %dth Oddonaci Time(ns): %d, Value: %d"+System.lineSeparator(), i, duration, iOddonacci)
                                .getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("IO Error: Something went wrong trying to write to "+FILENAME);
        }
    }

    /**
     * Oddonacci is a modification of Fibonacci.
     * It sums the previous 3 values and starts with {1,1,1}
     * Due to branching 3 times per recursive call, it has O(n^3).
     * @param k - Non-negative integer k. Used for reaching recursive base case
     * @return - The kth Fibonacci number
     */
    public static BigInteger Oddonacci(int k) {
        // Oddonacci 1,2,3 have a default value of 1
        if(k == 3 || k == 2 || k == 1)
            return new BigInteger("1");
        return Oddonacci(k-1).add(Oddonacci(k-2).add(Oddonacci(k-3)));
    }
}
