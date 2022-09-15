class Solution {

    // Time: O(N)
    // Space: O(N)
    // subarray多想前綴和
    public int maxSubArray(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i + 1] = sum;
        }

        int prevMin = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length + 1; i++) {
            max = Math.max(max, prefixSum[i] - prevMin);
            prevMin = Math.min(prevMin, prefixSum[i]);
        }

        return max;
    }
}