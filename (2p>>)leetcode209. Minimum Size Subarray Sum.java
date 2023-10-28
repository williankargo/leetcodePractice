// TC: O(N)
// SC: O(1)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int length = Integer.MAX_VALUE;

        int j = 0; // 此處不當ans的後一個index
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {

            while (j < nums.length && (sum + nums[j]) < target) {
                sum += nums[j];
                j++;
            }

            if (j < nums.length && sum + nums[j] >= target) {
                length = Math.min(length, j - i + 1);
            }

            sum -= nums[i];
        }

        return length == Integer.MAX_VALUE ? 0 : length;
    }
}