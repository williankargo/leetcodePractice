class Solution {
    // TC: O(N)
    // SC: O(N)
    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;

        int[] hold = new int[n]; // now hold the stock, current wealth
        hold[0] = -prices[0];

        int[] free = new int[n]; // now don't hold the stock, current wealth
        free[0] = 0;

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], free[i - 1] - prices[i]);
            free[i] = Math.max(free[i - 1], prices[i] + hold[i - 1] - fee);
        }

        return free[n - 1];
    }
}