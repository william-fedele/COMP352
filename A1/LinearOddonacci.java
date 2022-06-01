
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Oddonacci Algorithm with Linear Complexity
 */
public class LinearOddonacci {

    static final String FILENAME = "out.txt";
    static final int NTH_ODDONACCI = 1000;

    public static void main(String[] args) {

        long startTime, duration;
        try {
            for (int i = 5; i <= NTH_ODDONACCI; i += 5) {
                System.out.println(String.format("Testing linear Oddonacci with n=%d",i));

                // Record execution time for the ith Oddonacci
                startTime=System.nanoTime();
                BigInteger[] iOddonacci = LinearOddonacci(i);
                duration=(System.nanoTime()-startTime);

                Files.write(Paths.get(FILENAME),
                        String.format("Linear %dth Oddonaci Time(ns): %d, Value: %d"+System.lineSeparator(), i, duration, iOddonacci[0])
                                .getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("IO Error: Something went wrong trying to write to "+FILENAME);
        }
    }

    /**
     * LinearOddonacci is a modification of trinary Oddonacci.
     * It sums the previous 3 values and starts with {1,1,1}
     * Due to storing the previous Oddonacci numbers at each level, it has O(n).
     * @param k - Non-negative integer k. Used for reaching recursive base case
     * @return - The kth,(k-1)th, and (k-2)th Oddonacci numbers in an array
     */
    public static BigInteger[] LinearOddonacci(int k) {
        // Oddonacci 1,2,3 are all 1, but require the previous Oddonacci values as well
        if(k == 3)
            return new BigInteger[]{new BigInteger("1"),new BigInteger("1"),new BigInteger("1")};
        else if(k == 2)
            return new BigInteger[]{new BigInteger("1"),new BigInteger("1"),new BigInteger("0")};
        else if(k == 1)
            return new BigInteger[]{new BigInteger("1"),new BigInteger("0"),new BigInteger("0")};
        else {
            BigInteger[] a = LinearOddonacci(k-1);
            BigInteger sum = a[0].add(a[1].add(a[2]));
            return new BigInteger[]{sum,a[0], a[1]};
        }
    }
}
