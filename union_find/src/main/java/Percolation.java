import edu.princeton.cs.algs4.WeightedQuickUnionUF; // !!! uses "official" implementation

public class Percolation {
    private static final int SOURCE = 0;
    private final int sink;
    private final int n;
    private final boolean[] sites;
    private final WeightedQuickUnionUF uf;
    private int openCount = 0;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        this.n = n;
        this.sink = n * n + 2 - 1; // 0 [1 2 3 4] 5 <- sink
        this.sites = new boolean[n * n + 2];  // n^2 + source + sink
        this.sites[SOURCE] = true;
        this.sites[sink] = true;
        this.uf = new WeightedQuickUnionUF(n * n + 2); // n^2 + source + sink
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        int i = id(row, col);
        if (sites[i]) {
            return;
        }
        sites[i] = true;
        openCount++;
        connect(i, left(row, col));
        connect(i, right(row, col));
        connect(i, up(row, col));
        connect(i, down(row, col));
    }

    // must be used only for neighbour sites
    private void connect(int i, int j) {
        if (i != -1 && j != -1 && sites[i] && sites[j]) {
            uf.union(i, j);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        return sites[id(row, col)];
    }

    // is the site (row, col) full?
    //
    // FAILED backwash tests:
    //   Test 19: check for backwash with predetermined sites that have
    //   multiple percolating paths
    //
    //   Test 20: call all methods in random order until all sites are open
    //   (these inputs are prone to backwash)
    //
    //   Test 21: substitute WeightedQuickUnionUF data type that picks leader nondeterministically;
    //         call all methods in random order until all sites are open
    //         (these inputs are prone to backwash)
    public boolean isFull(int row, int col) {
        check(row, col);
        return uf.find(SOURCE) == uf.find(id(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(SOURCE) == uf.find(sink);
    }

    private void check(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("row and col must be >= 1 and <= n row=" + row + " col=" + col);
        }
    }

    private int id(int row, int col) {
        return (row - 1) * n + col;
    }

    private int left(int row, int col) {
        if (col == 1) {
            return -1;
        }
        return id(row, col - 1);
    }

    private int right(int row, int col) {
        if (col == n) {
            return -1;
        }
        return id(row, col + 1);
    }

    private int up(int row, int col) {
        if (row == 1) {
            return SOURCE;
        }
        return id(row - 1, col);
    }

    private int down(int row, int col) {
        if (row == n) {
            return sink;
        }
        return id(row + 1, col);
    }

    // test client (optional)
    public static void main(String[] args) {
        throw new RuntimeException("");
    }
}