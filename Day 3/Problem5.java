import java.util.ArrayList;

public class Problem5 {
    private static int partSum(ArrayList<Integer> freq, int start, int end) {
        int result = 0;
        for (int i=start; i<=end; i++) {
            result += freq.get(i);
        }
        return result;
    }

    public static int optimalCost(ArrayList<Integer> keys, ArrayList<Integer> freq, int n) {
        int[][] dp = new int[keys.size()][keys.size()];
        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }

        return dpTraverser(dp, freq, 0, n-1);
    }

    private static int dpTraverser(int[][] dp, ArrayList<Integer> freq, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int minCost = Integer.MAX_VALUE;
        for (int i=left; i<=right; i++) {
            int currCost = partSum(freq, left, right) + dpTraverser(dp, freq, i+1, right) + dpTraverser(dp, freq, left, i-1);
            minCost = Math.min(minCost, currCost);
        }

        dp[left][right] = minCost;
        return minCost;
    }

    // Testing
    private static ArrayList<Integer> convertToArrayList(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i : nums) arr.add(i);
        return arr;
    }
    public static void main(String[] args) {
        ArrayList<Integer> keys, freq; int n;

        keys = convertToArrayList(new int[]{1, 3, 5});
        freq = convertToArrayList(new int[]{3, 10, 7});
        n = 3;
        System.out.println(Problem5.optimalCost(keys, freq, n));

        keys = convertToArrayList(new int[]{1, 2, 3});
        freq = convertToArrayList(new int[]{25, 10, 20});
        n = 3;
        System.out.println(Problem5.optimalCost(keys, freq, n));

        keys = convertToArrayList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        freq = convertToArrayList(new int[]{19, 2, 4, 3, 7, 2, 8, 20, 2, 20});
        n = 10;
        System.out.println(Problem5.optimalCost(keys, freq, n));
    }
}