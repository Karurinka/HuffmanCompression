package tests;

import logic.HuffmanManager;
import logic.Logic;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerformanceTest
{
    private HuffmanManager manager;
    private String stringPerformance10k;
    private String stringPerformance1mil;

    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final Logger logger = Logger.getLogger(HuffmanManager.class.getName());

    @Before
    public void setUp() throws Exception
    {
        manager = new HuffmanManager(new Logic());
        stringPerformance10k = generateTestString(10000);
        stringPerformance1mil = generateTestString(1000000);
    }

    @Test
    public void compressTest()
    {
        // performance test encode with 10k words
        long startTime10k = System.nanoTime();
        manager.encode(stringPerformance10k);
        long resultTime10k = System.nanoTime() - startTime10k;
        String logMessage10k = String.format("encode message 10k words - time measured: %d nanoseconds", resultTime10k);
        logger.log(Level.INFO, logMessage10k);

        // performance test encode with 1mil words
        long startTime1mil = System.nanoTime();
        manager.encode(stringPerformance1mil);
        long resultTime1mil = System.nanoTime() - startTime1mil;
        String logMessage1mil = String.format("encode message 1mil words- time measured: %d nanoseconds", resultTime1mil);
        logger.log(Level.INFO, logMessage1mil);
    }

    @Test
    public void decompressTest() throws IllegalAccessException
    {
        // performance test encode with 10k words
        long startTime10k = System.nanoTime();
        manager.decode(manager.encode(stringPerformance10k));
        long resultTime10k = System.nanoTime() - startTime10k;
        String logMessage10k = String.format("decode message 10k words - time measured: %d nanoseconds", resultTime10k);
        logger.log(Level.INFO, logMessage10k);

        // performance test encode with 1mil words
        long startTime1mil = System.nanoTime();
        manager.decode(manager.encode(stringPerformance1mil));
        long resultTime1mil = System.nanoTime() - startTime1mil;
        String logMessage1mil = String.format("decode message 1mil words- time measured: %d nanoseconds", resultTime1mil);
        logger.log(Level.INFO, logMessage1mil);
    }

    private String generateTestString(int length)
    {
        StringBuilder output = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < length; i++)
        {
            output.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }

        return output.toString();
    }
}
