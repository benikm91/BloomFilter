package bkm.bloomfilter;
import static java.lang.Math.*;

/**
 * Created by Benjamin on 29.11.2015.
 */
public class BloomFilter {

    /**
     * Get the info for the best BloomFilter configuration by the number of expected elements in the BloomFilter.
     * @param n Number of expected elements in BloomFilter.
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
