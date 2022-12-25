/**
 * WQU:
 *  init      O(N)
 *  union     O(lg N) ~ O(1) in case of given roots OR O(proportional to depth of p and q)
 *  find      O(lg N) ~ O(proportional to depth of p and q)
 *  connected O(lg N)
 *
 *  BUT: depth of any p and q is at most lg N
 *  Proposition. Depth of any node x is at most lg N (lg == log_2).
 *  Pf. Depth of x increases when T1 containing x is merged with T2.
 *  - the size of the tree containing x at lease doubles (|T1| >= |T2|)
 *  - the size of the tree containing x can double at most lg N times.
 *
 *                   0123
 *                 /     \
 *               01       23         <- depth of the merging operations tree is at most lg N
 *              /  \    /   \
 *            0     1  2     3
 */
public class WeightedQuickUnionUF {
    private int count;
    private final int[] id;
    private final int[] sz; // sz[i] == size of tree with root in id[i]


    public WeightedQuickUnionUF(int n) {
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
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
