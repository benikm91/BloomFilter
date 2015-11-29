package main.bloomfilter;

/**
 * Created by Benjamin on 29.11.2015.
 */
public class BloomFilterInfo {

    /** Filter size */
    public final long m;
    /** Error probability */
    public final double p;
    /** number of expected results */
    public final long n;
    /** number of hash functions */
    public final long k;

    public BloomFilterInfo(long m, double p, long n, long k) {
        this.m = m;
        this.p = p;
        this.n = n;
        this.k = k;
    }


    @Override
    public String toString() {
        return String.format("m = %s, p = %s, n = %s, k = %s", m, p, n, k);
    }

}
