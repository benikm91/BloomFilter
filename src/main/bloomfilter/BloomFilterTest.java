package main.bloomfilter;

import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class BloomFilterTest {

    @Test
    public void testGetInfo() {
        // Results from http://hur.st/bloomfilter?n=100&p=0.1
        BloomFilterInfo info = BloomFilter.getInfo(100, 0.1);
        assertTrue(info.n == 100);
        assertTrue(info.p == 0.1);
        assertTrue(info.k == 3);
        assertTrue(info.m == 480);
    }

    @Test
    public void testAdd() {
        BloomFilter bf = new BloomFilter(100, 0.1);
        bf.add("Test");
        bf.add("Test2");
        assertTrue(bf.containsMaybe("Test"));
        assertTrue(bf.containsMaybe("Test2"));
        assertFalse(bf.containsMaybe("NotInBF"));
    }

}
