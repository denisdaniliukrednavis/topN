import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TopListTest {

    private static List<Integer> testArray = null;
    private static Integer maxN = 100000000;

    @Test
    public void topN() {
        long tStart = System.currentTimeMillis();
        System.out.println("SetUp test data...");

        testArray = Stream.iterate(0, value -> value + 1).limit(maxN).collect(Collectors.toList());
        Collections.shuffle(testArray);

        System.out.println("Done in " + (System.currentTimeMillis() - tStart) + " ms");

        testArray = TopList.topN(testArray.stream(), 10);

        Assert.assertEquals(testArray.get(0), (Integer) (maxN-1));
        Assert.assertEquals(testArray.get(1), (Integer) (maxN-2));
    }

    @Test
    public void topNEmpty() {
        testArray = TopList.topN(null, 10);

        Assert.assertNotNull(testArray);
        Assert.assertEquals(testArray.size(), 0);
    }

    @Test
    public void topNDefault() {
        long tStart = System.currentTimeMillis();
        System.out.println("SetUp test data...");

        testArray = Stream.iterate(0, value -> value + 1).limit(100).collect(Collectors.toList());
        Collections.shuffle(testArray);

        System.out.println("Done in " + (System.currentTimeMillis() - tStart) + " ms");

        testArray = TopList.topN(testArray.stream(), null);

        Assert.assertNotNull(testArray);
        Assert.assertEquals(testArray.size(), 10);
    }
}