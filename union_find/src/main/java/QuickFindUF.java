/**
 * init      O(N)
 * union     O(N) <- slow
 * find      O(1) <- quick
 * connected O(1)
 */
public class QuickFindUF {
    private int count;
    private final int[] id;

    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        int pid = id[p];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = id[q];
        }
        count--;
    }

    public int find(int p) {
        return id[p];
    }

    public int count() {
        return count;
    }
}
