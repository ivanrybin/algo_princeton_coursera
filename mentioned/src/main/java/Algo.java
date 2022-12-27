public class Algo {

    public static int binarySearch(int[] xs, int l, int r, int key) {
        r = r - 1; // exclusive
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (key > xs[m]) {
                l = m + 1;
            } else if (key < xs[m]) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static int leftMostBinarySearch(int[] xs, int l, int r, int key) {
        int i = binarySearch(xs, l, r, key);
        int j = i;
        while (j >= l) {
            j = binarySearch(xs, l, j, key);
            if (j >= l) {
                i = j;
            }
        }
        return i;
    }

    public static int rightMostBinarySearch(int[] xs, int l, int r, int key) {
        int i = binarySearch(xs, l, r, key);
        int j = i;
        while (j >= l && j < r) {
            j = binarySearch(xs, j + 1, r, key);
            if (j >= l && j < r) {
                i = j;
            }
        }
        return i;
    }
}
