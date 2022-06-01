
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Oddonacci Algorithm with Linear Complexity using Tail Recursion
 */
public class TailLinearOddonacci {

    static final String FILENAME = "outTAIL.txt";
    static final int NTH_ODDONACCI = 1000;

    public static void main(String[] args) {

        long startTime, duration;
        try {
            for (int i = 5; i <= NTH_ODDONACCI; i += 5) {
                System.out.println(String.format("Testing tail linear Oddonacci with n=%d",i));

                // Record execution time for the ith Oddonacci
                startTime=System.nanoTime();
                BigInteger iOddonacci = LinearOddonacciTail(i, new BigInteger("1"), new BigInteger("1"), new BigInteger("1"));
                duration=(System.nanoTime()-startTime);

                Files.write(Paths.get(FILENAME),
                        String.format("Tail Linear %dth Oddonacci Time(ns): %d, Value: %d"+System.lineSeparator(), i, duration, iOddonacci)
                                .getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("IO Error: Something went wrong trying to write to "+FILENAME);
        }
    }

    /**
     *  LinearOddonacciTail is a tail-recursive version of LinearOddonacci
     * @param k - Non-negative integer k. Used for reaching recursive base case
     * @param x - Stores kth fibonacci number
     * @param y - Stores (k-1)th fibonacci number
     * @param z - Stores (k-2)th fibonacci number
     * @return - The kth fibonacci number
     */
    public static BigInteger LinearOddonacciTail(int k, BigInteger x, BigInteger y, BigInteger z) {
        if(k <= 3) return x;
        return LinearOddonacciTail(k-1, x.add(y.add(z)), x, y);
    }
}
