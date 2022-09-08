class Solution {

    // Time: O(n)
    // Space: O(1)
    public int minimumAverageDifference(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }

        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        long min = Long.MAX_VALUE, leftSum = 0, rightSum = 0;
        int currentMinIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum += nums[i];
            rightSum = totalSum - leftSum;
            long abs = Math.abs((leftSum / (i + 1)) - (i == nums.length - 1 ? 0 : rightSum / (nums.length - i - 1)));

            if (abs < min) {
                min = abs;
                currentMinIndex = i;
            }
        }

        return currentMinIndex;
    }
}