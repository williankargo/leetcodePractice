import java.util.ArrayList;
import java.util.List;

class Solution1 {

    // Time: O(n^2)
    // Space: O(n)
    public int longestIncreasingSubsequence(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return 0;
        }

        // 0.
        // 以index=i為結尾的LIS
        int n = nums.length;
        int[] dp = new int[n];

        // 1.
        // initialization 可以在寫decide時再決定要怎麼寫
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // 2.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // be careful
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}

// follow-up: 印出那個 LIS 一路來的數字
// dp雖不擅於求具體方案，但關於最值的具體方案還是可以的
class Solution2 {

    public int longestIncreasingSubsequence(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return 0;
        }

        // 0.
        // 以index=i為結尾的LIS
        int n = nums.length;
        int[] dp = new int[n];
        // prev: dp[i] 是由哪個 dp[j] 成就的
        int[] prev = new int[n];

        // 1.
        // initialization 可以在寫decide時再決定要怎麼寫
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            // -1 : 沒有依賴j
            prev[i] = -1;
        }

        // 2.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1; // be careful
                    prev[i] = j;
                }
            }
        }

        int max = 0;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > max) {
                max = dp[i];
                last = i;
            }
        }

        // follow-up: 印出路徑
        List<Integer> result = new ArrayList<>();
        while (last != -1) {
            result.add(nums[last]);
            last = prev[last];
        }
        int length = result.size();
        for (int i = length - 1; i >= 0; i++) {
            System.out.println(result.get(i) + " - ");
        }

        return max;
    }
}