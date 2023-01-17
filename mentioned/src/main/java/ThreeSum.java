import java.util.Arrays;

public class ThreeSum {

    public static int threeSum(int[] xs) {
        int cnt = 0;
        Arrays.sort(xs);
        for (int i = 0; i < xs.length; i++) {
            for (int j = i + 1; j < xs.length; j++) {
                int lm = Algo.leftMostBinarySearch(xs, j + 1, xs.length, -(xs[i] + xs[j]));
                int rm = Algo.rightMostBinarySearch(xs, j + 1, xs.length, -(xs[i] + xs[j]));
                if (lm != -1 && rm != -1) {
                    cnt += rm - lm + 1;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] xs = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        System.out.println(threeSum(xs));
    }
}
