package test;

import junit.framework.TestCase;
import main.bloomfilter.BloomFilter;
import main.bloomfilter.BloomFilterInfo;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testWords() {
        double expectedP = 0.1;
        // setup : load words from words.txt into a BloomFilter.
        List<String> words = getWords(new File("res/test/words.txt"));
        System.out.println(words.size());
        BloomFilter bf = new BloomFilter(words.size(), expectedP);
        words.forEach(bf::add);
        // test expectedP.
        List<String> notWords = getWords(new File("res/test/wordsNotInWords.txt"));
        long numOfFalsePositives = notWords.stream().map(bf::containsMaybe).filter(r -> r).count();
        double actualP = ((double) numOfFalsePositives) / notWords.size();
        assertTrue(String.format("BloomFilter had unusual high differentiation in expected and actual p. Expected P: %s, actual P: %s", expectedP, actualP), Math.abs(expectedP - actualP) < 0.1);
    }

    /**
     * Help function for loading test data from files.
     * @param file File with test data.
     * @return Lines of the file content.
     */
    private List<String> getWords(File file) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
