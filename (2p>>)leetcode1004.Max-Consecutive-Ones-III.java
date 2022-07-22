class Solution {

    // 因為有分段性 -> 同向雙指針（滑動窗口）
    // 轉換為 (全部當1加起來) - (全部的數值總和)
    // Time: O(N)
    // Space: O(1)
    public int longestOnes(int[] nums, int k) {

        // 0.
        int maxLength = 0;
        // 1.
        int total = 0;

        // 2.
        for (int i = 0, j = 0; i < nums.length; i++) { // j: 尾部的後一個點

            // 3.
            while (j < nums.length && ((j - i + 1) - (total + nums[j])) <= k) { // j 移動, (j - i + 1) 代表全部全部1加起來 / 先判斷未來的
                total += nums[j];
                j++;
            }

            // 4.
            if (j < nums.length && !(((j - i + 1) - (total + nums[j])) <= k)) { // 超過k了
                maxLength = Math.max(maxLength, (j - i));
            } else { // j走完了
                return Math.max(maxLength, (j - i));
            }

            // 5.
            total -= nums[i];
        }

        return maxLength;
    }
}