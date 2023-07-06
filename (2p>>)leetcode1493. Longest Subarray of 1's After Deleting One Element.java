class Solution {

    // TC: O(N)
    // SC: O(1)
    // related to leetcode 1004
    public int longestSubarray(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        // 0.
        int j = 0;

        // 1.
        int maxCount = 0;
        int count = 0;
        int k = 1;

        // 2.
        for (int i = 0; i < nums.length; i++) {

            // 3.
            while (j < nums.length && (nums[j] == 1 || nums[j] == 0 && k > 0)) {
                if (nums[j] == 0) {
                    k--;
                }
                j++;
                count++;
            }

            // 4.
            maxCount = Math.max(maxCount, count);
            if (j >= nums.length) {
                return maxCount - 1;
            }

            // 5.
            if (nums[i] == 1) {
                count--;
            } else if (nums[i] == 0) {
                count--;
                k++;
            }

        }

        return maxCount - 1;
    }
}