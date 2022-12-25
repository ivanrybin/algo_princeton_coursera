import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 0;
        String w = null;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            i++;
            if (StdRandom.bernoulli(1.0 / (double) i)) {
                w = s;
            }
        }
        StdOut.println(w);
    }
}
