package bkm.bloomfilter;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BloomFilterTest {

    @Test
    public void testGetInfo() {
        //http://hur.st/bloomfilter?n=100&p=0.1
        BloomFilterInfo info = BloomFilter.getInfo(100, 0.1);
        assertTrue(info.n == 100);
        assertTrue(info.p == 0.1);
        assertTrue(info.k == 3);
        assertTrue(info.m == 480);
    }

}
