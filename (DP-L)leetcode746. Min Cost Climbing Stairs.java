
import java.util.HashMap;
import java.util.Map;

// method 1: DP-L
// TC: O(N)
// SC: O(N)
class Solution1 {

    public int minCostClimbingStairs(int[] cost) {

        // 1.
        // minCost[n] == how much cost to go to "n" not including the cost of n
        int[] minCost = new int[cost.length + 1]; // +1 => floor

        // 2.
        minCost[0] = 0;
        minCost[1] = 0;

        // 3.
        for (int i = 2; i <= cost.length; i++) {
            int oneStep = minCost[i - 1] + cost[i - 1];
            int twoStep = minCost[i - 2] + cost[i - 2];
            minCost[i] = Math.min(oneStep, twoStep);
        }

        // 4.
        return minCost[minCost.length - 1];
    }
}

// method 2: dfs
// TC: O(N)
// SC: O(N)
class Solution2 {

    public int minCostClimbingStairs(int[] cost) {

        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 0);
        return dfs(cost.length, cost, memo);
    }

    // 走到n + 1的成本是多少？不包含n+1本身
    private int dfs(int index, int[] cost, Map<Integer, Integer> memo) {

        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int oneStep = dfs(index - 1, cost, memo) + cost[index - 1];
        int twoStep = dfs(index - 2, cost, memo) + cost[index - 2];
        int sum = Math.min(oneStep, twoStep);
        memo.put(index, sum);
        return sum;
    }
}