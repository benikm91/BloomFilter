package main.bloomfilter;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.BitSet;

import static java.lang.Math.*;

/**
 * Created by Benjamin on 29.11.2015.
 */
public class BloomFilter {

    BitSet table;
    int m;
    HashFunction[] hashes;

    public BloomFilter(int n, double p) {
        BloomFilterInfo info = BloomFilter.getInfo(n, p);
        // generate info.k hash functions
        hashes = new HashFunction[(int) info.k];
        for (int i = 0; i < hashes.length; i++) hashes[i] = Hashing.murmur3_128((int) (Math.random() * 10000));
        // generate info.m bits.
        m = (int) info.m;
        table = new BitSet(m);
    }

    public void add(String toAdd) {
        for (HashFunction hf : hashes) {
            int index = Math.abs(hf.hashString(toAdd).asInt() % m);
            table.set(index);
        }
    }

    public boolean containsMaybe(String search) {
        for (HashFunction hf : hashes) {
            int index = Math.abs(hf.hashString(search).asInt() % m);
            if (!table.get(index)) return false;
        }
        return true;
    }

    /**
     * Get the info for the best BloomFilter configuration by the number of expected elements in the BloomFilter.
     * @param n Number of expected elements in BloomFilter.
     * @param p Desired false positive probability
     * @return info for best BloomFilter configuration.
     */
    public static BloomFilterInfo getInfo(int n, double p) {
        double m = -(n * log(p) / pow(log(2), 2));
        double k = log(2)*(m / n);
        return new BloomFilterInfo(
            (long) ceil(m),
            p,
            round(n),
            round(k)
        );
    }


}
