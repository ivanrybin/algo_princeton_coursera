/**
 * WQUPC
 *  1 * init           O(N)
 *  M * union-find     O(M * lg* N)
 *
 *  lg* N == count of lg operation until it will be 1:
 *   1 - 1
 *   2 - 1
 *   4 - 2
 *   16 - 3
 *   65536 - 4
 *   2^65536 - 5
 *
 *  Paths compressed each time when union is called.
 */
public class WeightedQuickUnionPathCompressionUF {
    private int count;
    private final int[] id;
    private final int[] sz; // sz[i] == size of tree with root in id[i]


    public WeightedQuickUnionPathCompressionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
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
        if (sz[pr] < sz[qr]) { // size of p tree is lower than size of q tree: connect root(p) to root(q)
            id[pr] = qr;
            sz[qr] += sz[pr];
        } else { // size of q tree is lower than size of p tree: connect root(q) to root(p)
            id[qr] = pr;
            sz[pr] += sz[qr];
        }
        count--;
    }

    public int find(int p) {
        return root(p);
    }

    public int count() {
        return count;
    }
}
