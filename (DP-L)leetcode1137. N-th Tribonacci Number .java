// method 1: dp

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    public int tribonacci(int n) {

        // 0. corner case
        if (n < 3) {
            return n > 0 ? 1 : 0;
        }

        // 1. def
        // dp[n] = Tn
        int[] dp = new int[n + 1];

        // 2. init
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        // 3. steps
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // 4. return
        return dp[n];
    }
}

// method 2: dfs
class Solution2 {

    Map<Integer, Integer> map = new HashMap<>() {
        {
            put(0, 0);
            put(1, 1);
            put(2, 1);
        }
    };

    public int tribonacci(int n) {
        return dfs(n);
    }

    private int dfs(int num) {

        if (map.containsKey(num)) {
            return map.get(num);
        }

        int sum = dfs(num - 1) + dfs(num - 2) + dfs(num - 3);
        map.put(num, sum);

        return sum;
    }

}
