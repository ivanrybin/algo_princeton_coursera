import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] stats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        stats = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = 1 + StdRandom.uniformInt(n);
                int col = 1 + StdRandom.uniformInt(n);
                p.open(row, col);
            }
            stats[i] = p.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(stats) - 1.96 * StdStats.stddev(stats) / Math.sqrt(stats.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(stats) + 1.96 * StdStats.stddev(stats) / Math.sqrt(stats.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}