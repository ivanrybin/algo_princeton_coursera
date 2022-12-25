/**
 * QU:
 *  init      O(N)
 *  union     O(N) <- slow
 *  find      O(N) <- slow
 *  connected O(1)
 */
public class QuickUnionUF {
    private int count;
    private final int[] id;

    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        int pr = root(p);
        int qr = root(q);
        id[pr] = qr;
        count--;
    }

    public int find(int p) {
        return root(p);
    }

    public int count() {
        return count;
    }
}
