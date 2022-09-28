class Solution {

    // Time: O(N)
    // Space: O(1)
    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int profit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            profit = Math.max(profit, prices[i] - minPrice);
        }

        return profit;
    }
}