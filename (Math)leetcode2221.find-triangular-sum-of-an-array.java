
// 數學解法
class Solution1 {

    // Time: O(N)
    // Space: O(1000 * 1000)
    // https://www.youtube.com/watch?v=RzoHl7M9xvM 可以知道是帕斯卡三角形
    public int triangularSum(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        long[][] combination = getCombination(1000);

        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += (nums[i] * combination[nums.length - 1][i] % 10);
        }

        return (int) (ans % 10);
    }

    // 組合模板，要背起來
    private long[][] getCombination(int n) { // 要預先產生C m = 多少 取

        long[][] combination = new long[n][n];
        int length = combination.length;
        for (int i = 0; i < length; i++) {
            combination[i][i] = combination[i][0] = 1;
            if (i == 0) {
                continue;
            }
            for (int j = 1; j < i; j++) {
                combination[i][j] = combination[i - 1][j - 1] + combination[i - 1][j];
                combination[i][j] %= 10; // 應題目要求
            }
        }

        return combination;
    }
}

// brute force
class Solution {

    // Time: O(n^2)
    // Space: O(1)
    public int triangularSum(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int length = nums.length;
        while (length > 1) {
            for (int i = 0; i < nums.length - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            length--;
        }

        return nums[0];
    }
}