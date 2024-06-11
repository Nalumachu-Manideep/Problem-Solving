import java.util.*;
class KnapsackProblemUsingMemorization {
    public static void main(String args[]) {
        int[] wt = {1, 2, 4, 5};
        int[] val = {5, 4, 8, 6};
        int w = 6;  // Adjusted to a realistic capacity
        int n = wt.length;
        int[][] dp = new int[n][w + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(ks(wt, val, wt.length - 1, w, dp));
    }

    public static int ks(int[] wt, int[] val, int ind, int w, int[][] dp) {
        if (ind == 0) {
            if (wt[0] <= w) {
                return val[0];
            }
            return 0;
        }
        
        if (dp[ind][w] != -1) {
            return dp[ind][w];
        }

        int notTake = ks(wt, val, ind - 1, w, dp);
        int take = Integer.MIN_VALUE;
        
        if (wt[ind] <= w) {
            take = val[ind] + ks(wt, val, ind - 1, w - wt[ind], dp);
        }
        
        dp[ind][w] = Math.max(notTake, take);
        return dp[ind][w];
    }
}
