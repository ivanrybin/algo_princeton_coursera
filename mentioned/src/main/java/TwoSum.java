import java.util.Arrays;

public class TwoSum {

    public static int twoSum(int[] xs) {
        int cnt = 0;
        Arrays.sort(xs);
        for (int i = 0; i < xs.length; i++) {
            int j = Algo.leftMostBinarySearch(xs, i + 1, xs.length, -xs[i]);
            while (j > 0) {
                cnt++;
                j = Algo.leftMostBinarySearch(xs, j + 1, xs.length, -xs[i]);
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        int[] xs = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        System.out.println(twoSum(xs));
    }
}
