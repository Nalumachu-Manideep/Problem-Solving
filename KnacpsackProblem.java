class KnapsackProblem {
    public static void main(String args[]) {
        int[] wt = {3, 2, 5};
        int[] val = {30, 40, 60};
        int w = 6;  // Adjusted to a realistic capacity
        
        System.out.println(ks(wt, val, wt.length - 1, w));
    }

    public static int ks(int[] wt, int[] val, int ind, int w) {
        if (ind < 0) {
            return 0;
        }
        
        if (wt[ind] > w) {
            return ks(wt, val, ind - 1, w);
        } else {
            int notTake = ks(wt, val, ind - 1, w);
            int Take = val[ind] + ks(wt, val, ind - 1, w - wt[ind]);
            return Math.max(notTake, Take);
        }
    }
}
